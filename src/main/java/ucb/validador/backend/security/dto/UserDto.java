package ucb.validador.backend.security.dto;

import java.util.Set;

import ucb.validador.backend.security.model.Role;

public class UserDto {
    private Long id;
    private String username;
    private String email;
    private Set<Role> roles;
    private Boolean status;

    public UserDto(Long id, String username, String email, Set<Role> roles, Boolean status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserDto [id=" + id + ", username=" + username + ", email=" + email + ", roles=" + roles + ", status="
                + status + "]";
    }

}
