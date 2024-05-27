package ucb.validador.backend.security.controller;

import java.util.*;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import ucb.validador.backend.security.exception.TokenRefreshException;
import ucb.validador.backend.security.model.ERole;
import ucb.validador.backend.security.model.LoginFail;
import ucb.validador.backend.security.model.PasswordSetting;
import ucb.validador.backend.security.model.RefreshToken;
import ucb.validador.backend.security.model.Role;
import ucb.validador.backend.security.model.User;
import ucb.validador.backend.security.dto.LoginRequestDto;
import ucb.validador.backend.security.dto.SignupRequestDto;
import ucb.validador.backend.security.dto.TokenRefreshRequestDto;
import ucb.validador.backend.security.dto.JwtResponseDto;
import ucb.validador.backend.security.dto.MessageResponseDto;
import ucb.validador.backend.security.dto.TokenRefreshResponseDto;
import ucb.validador.backend.security.repository.LoginFailRepository;
import ucb.validador.backend.security.repository.PasswordSettingRepository;
import ucb.validador.backend.security.repository.RoleRepository;
import ucb.validador.backend.security.repository.UserRepository;
import ucb.validador.backend.security.jwt.JwtUtils;
import ucb.validador.backend.security.service.RefreshTokenService;
import ucb.validador.backend.security.service.UserDetailsImpl;
import ucb.validador.backend.security.dto.UserDto;

import org.springframework.security.access.prepost.PreAuthorize;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    LoginFailRepository loginFailRepository;

    @Autowired
    PasswordSettingRepository passwordSettingRepository;

    @Autowired
    private JavaMailSender emailSender;

    @GetMapping("/passwordsetting")
    public ResponseEntity<?> getPasswordSetting() {
        List<PasswordSetting> passwordSettings = passwordSettingRepository.findAll();
        if (passwordSettings.size() <= 0) {
            log.error("Error: No password setting");
            return ResponseEntity.badRequest().body(new MessageResponseDto("Error: No password setting"));
        }
        PasswordSetting passwordSetting = passwordSettings.get(passwordSettings.size() - 1);
        return ResponseEntity.ok(passwordSetting);
    }

    @PostMapping("/passwordsetting")
    public ResponseEntity<PasswordSetting> postPasswordSetting(@RequestBody PasswordSetting passwordSetting) {
        PasswordSetting response = passwordSettingRepository.save(passwordSetting);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/read")
    @PreAuthorize("hasRole('ORGANIZER')")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> userDtos = userRepository.findAll().stream().map(this::userToUserDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/read/{userId}")
    @PreAuthorize("hasRole('ORGANIZER') or hasRole('REFEREE') or hasRole('COACH')")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") Long userId) throws Exception {
        UserDto userDto = userRepository.findById(userId).stream().map(this::userToUserDto).findFirst()
                .orElseThrow(() -> new Exception("Could not find user"));

        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/signin")
    @Transactional
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequest) {
        LoginFail loginFail = loginFailRepository
                .findByUserId(userRepository.findByUsername(loginRequest.getUsername()).get().getId());
        if (loginFail.getExpireDate() != null) {
            Date expireDate = loginFail.getExpireDate();
            Date currentDate = new Date();
            System.out.println("Usuario: " + loginFail.getExpireDate());
            if (expireDate.compareTo(currentDate) < 0) {
//                loginFailRepository
//                        .login_fail_true(userRepository.findByUsername(loginRequest.getUsername()).get().getId());
                System.out.println("loginFail.getCount() = " + loginFail.getCount());
                loginFailRepository.loginFailTruePart1(
                        userRepository.findByUsername(loginRequest.getUsername()).get().getId());

                loginFailRepository.loginFailTruePart2(
                        userRepository.findByUsername(loginRequest.getUsername()).get().getId());
                return ResponseEntity.badRequest().body(new MessageResponseDto("User unlocked successfully!"));
            }
        }

        if (!userRepository.findByUsername(loginRequest.getUsername()).get().getStatus()) {
            return ResponseEntity.badRequest().body(new MessageResponseDto("Error: User not allowed"));
        }
        try {

            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            String jwt = jwtUtils.generateJwtToken(userDetails);

            List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

            return ResponseEntity.ok(new JwtResponseDto(jwt, refreshToken.getToken(), userDetails.getId(),
                    userDetails.getUsername(), userDetails.getEmail(), roles));
        } catch (Exception e) {
            loginFail.setCount(loginFail.getCount() + 1);
            loginFailRepository.save(loginFail);
            if (loginFail.getCount() >= 3) {
//                loginFailRepository
//                        .login_fail_false(userRepository.findByUsername(loginRequest.getUsername()).get().getId());
                loginFailRepository.loginFailFalsePart1(
                        userRepository.findByUsername(loginRequest.getUsername()).get().getId());

                loginFailRepository.loginFailFalsePart2(
                        userRepository.findByUsername(loginRequest.getUsername()).get().getId());
                return ResponseEntity.badRequest()
                        .body(new MessageResponseDto("Error: Tried too many times. Wait 1 minute and try again."));
            }
            return ResponseEntity.badRequest().body(new MessageResponseDto("Error: Bad credentials"));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestDto signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponseDto("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponseDto("Error: Email is already in use!"));
        }

        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_COACH)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "organizer":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ORGANIZER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "referee":
                        Role modRole = roleRepository.findByName(ERole.ROLE_REFEREE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_COACH)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        User result = userRepository.save(user);
        loginFailRepository.save(new LoginFail(null, result.getId()));

        return ResponseEntity.ok(new MessageResponseDto("User registered successfully!"));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ORGANIZER')")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id,
            @Valid @RequestBody SignupRequestDto signUpRequest) {
        if (!userRepository.getReferenceById(id).getUsername().equals(signUpRequest.getUsername())) {
            if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                return ResponseEntity.badRequest().body(new MessageResponseDto("Error: Username is already taken!"));
            }
        }
        if (!userRepository.getReferenceById(id).getEmail().equals(signUpRequest.getEmail())) {
            if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                return ResponseEntity.badRequest().body(new MessageResponseDto("Error: Email is already in use!"));
            }
        }

        User user = userRepository.getReferenceById(id);
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_COACH)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "organizer":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ORGANIZER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "referee":
                        Role modRole = roleRepository.findByName(ERole.ROLE_REFEREE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_COACH)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponseDto("User updated successfully!"));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ORGANIZER')")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id) {
        User user = userRepository.getReferenceById(id);
        user.setStatus(user.getStatus() ? false : true);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponseDto(
                user.getStatus() ? "User activated successfully!" : "User deleted successfully!"));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequestDto request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken).map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser).map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponseDto(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Long userId = userDetails.getId();
        refreshTokenService.deleteByUserId(userId);
        return ResponseEntity.ok(new MessageResponseDto("Log out successful!"));
    }

    public UserDto userToUserDto(User user) {
        UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getRoles(),
                user.getStatus());
        return userDto;
    }

    // Recuperacion de Contraseña
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Error: User is not found with email: " + email));
        String token = UUID.randomUUID().toString();
        user.setResetPasswordToken(token);
        userRepository.save(user);

        // Enviar correo electronico
        String resetLink = "http://localhost:4200/reset-password?token=" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Reset Password");
        message.setText("Click the folowing link to reset you password: " + resetLink);
        emailSender.send(message);
        return ResponseEntity.ok(new MessageResponseDto("Reset password link has been sent to your email."));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword){
        User user = userRepository.findByResetPasswordToken(token)
                .orElseThrow(() ->  new RuntimeException("Error: User is not found with token." + token));
        // Restablecer contraseña
        user.setPassword(encoder.encode(newPassword));
        user.setResetPasswordToken(null);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponseDto("Password reset successfully!") );
    }

}