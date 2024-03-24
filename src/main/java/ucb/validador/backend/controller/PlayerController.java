package ucb.validador.backend.controller;

import ucb.validador.backend.dto.PlayerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ucb.validador.backend.service.PlayerService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<PlayerDto>> getPlayers() {
        List<PlayerDto> playerDtos = playerService.findAllDto();
        return new ResponseEntity<>(playerDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postPlayer(@RequestParam(value = "name") String name,
            @RequestParam(value = "profile") MultipartFile profile, @RequestParam(value = "ci") String ci,
            @RequestParam(value = "birthdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthdate,
            @RequestParam(value = "positionId") Integer positionId, @RequestParam(value = "teamId") Integer teamId) {
        try {
            String response = playerService.saveDto(name, profile, ci, birthdate, positionId, teamId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Error posting player", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{playerId}")
    public ResponseEntity<String> putPlayer(@PathVariable("playerId") Integer playerId,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "profile") MultipartFile profile, @RequestParam(value = "ci") String ci,
            @RequestParam(value = "birthdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthdate,
            @RequestParam(value = "positionId") Integer positionId, @RequestParam(value = "teamId") Integer teamId) {
        try {
            String response = playerService.updateDto(playerId, name, profile, ci, birthdate, positionId, teamId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Error updating player", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{playerId}")
    public ResponseEntity<String> deleteTeam(@PathVariable("playerId") Integer playerId) {
        try {
            String response = playerService.deleteByIdDto(playerId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Error deleting player", HttpStatus.BAD_REQUEST);
        }
    }

    // -----CUSTOM CONTROLLER-----
    @GetMapping(path = "/teamId/{teamId}")
    public ResponseEntity<List<PlayerDto>> getPlayersByTeamId(@PathVariable("teamId") Integer teamId) {
        List<PlayerDto> players = playerService.findAllByTeamIdDto(teamId);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping(path = "/userId/{userId}")
    public ResponseEntity<List<PlayerDto>> getPlayersByUserId(@PathVariable("userId") Integer userId) {
        List<PlayerDto> players = playerService.findAllByUserIdDto(userId);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping(path = "/tournamentTeamId/{tournamentTeamId}")
    public ResponseEntity<List<PlayerDto>> getPlayersByTournamentTeamId(
            @PathVariable("tournamentTeamId") Integer tournamentTeamId) {
        List<PlayerDto> players = playerService.findAllByTournamentTeamIdDto(tournamentTeamId);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }
}
