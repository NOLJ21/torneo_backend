package ucb.validador.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ucb.validador.backend.dto.PlayerDto;
import ucb.validador.backend.model.Player;
import ucb.validador.backend.repository.PlayerRepository;
//import ucb.validador.backend.s3.FileService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
//    private FileService fileService;

    @Autowired
    private FileService fileService;

    public List<PlayerDto> findAllDto() {
        return playerRepository.findAll().stream().map(this::playerToPlayerDto).collect(Collectors.toList());
    }

    public String saveDto(String name, MultipartFile profile, String ci, LocalDate birthdate, Integer positionId,
            Integer teamId) {
//        String imageName = fileService.uploadFile(profile);
        String imageName = fileService.uploadFile(profile);
        Player player = new Player(null, name, imageName, ci,
                birthdate, positionId, teamId);
        playerRepository.save(player);
        return "Successfully created player";
    }

    public String updateDto(Integer playerId, String name, MultipartFile profile, String ci, LocalDate birthdate,
            Integer positionId,
            Integer teamId) {
//        String imageName = fileService.uploadFile(profile);
        String imageName = fileService.uploadFile(profile);
        Player playerFound = playerRepository.getReferenceById(playerId);
        playerFound.setName(name);
        playerFound.setProfile(imageName);
        playerFound.setCi(ci);
        playerFound.setBirthdate(birthdate);
        playerFound.setPositionId(positionId);
        playerFound.setTeamId(teamId);
        playerRepository.save(playerFound);
        return "Successfully updated player";
    }

    public String deleteByIdDto(Integer playerId) {
        Player playerFound = playerRepository.getReferenceById(playerId);
        playerFound.setStatus(playerFound.getStatus() ? false : true);
        playerRepository.save(playerFound);
        return "Successfully deteled player";
    }

    // ----- CUSTOM SERVICES -----
    public List<PlayerDto> findAllByTeamIdDto(Integer teamId) {
        return playerRepository.findAllByTeamId(teamId).stream().map(this::playerToPlayerDto)
                .collect(Collectors.toList());
    }

    public List<PlayerDto> findAllByUserIdDto(Integer userId) {
        return playerRepository.findAllByUserId(userId).stream().map(this::playerToPlayerDto)
                .collect(Collectors.toList());
    }

    public List<PlayerDto> findAllByTournamentTeamIdDto(Integer tournamentTeamId) {
        return playerRepository.findAllByTournamentTeamId(tournamentTeamId).stream().map(this::playerToPlayerDto)
                .collect(Collectors.toList());
    }

    // ----- MODEL TO DTO -----
    private PlayerDto playerToPlayerDto(Player player) {
        String profile = fileService.getDownloadUrl(player.getProfile());
        PlayerDto playerDto = new PlayerDto(player.getId(), player.getName(), profile, player.getCi(),
                player.getBirthdate(),
                player.getPositionId(), player.getTeamId(),
                player.getStatus());
        return playerDto;
    }
}
