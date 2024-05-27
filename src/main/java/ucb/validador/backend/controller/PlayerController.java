package ucb.validador.backend.controller;

import ucb.validador.backend.dto.PlayerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ucb.validador.backend.service.PlayerService;

import java.time.LocalDate;
import java.util.List;

@Slf4j
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
        log.info("Solicitud para obtener todos los jugadores");
        List<PlayerDto> playerDtos = playerService.findAllDto();
        log.info("Jugadores obtenidos: {}", playerDtos);
        return new ResponseEntity<>(playerDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postPlayer(@RequestParam(value = "name") String name,
                                             @RequestParam(value = "profile") MultipartFile profile,
                                             @RequestParam(value = "ci") String ci,
                                             @RequestParam(value = "birthdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthdate,
                                             @RequestParam(value = "positionId") Integer positionId,
                                             @RequestParam(value = "teamId") Integer teamId) {
        log.info("Solicitud para registrar un nuevo jugador: nombre={}, ci={}, birthdate={}, positionId={}, teamId={}",
                name, ci, birthdate, positionId, teamId);
        try {
            String response = playerService.saveDto(name, profile, ci, birthdate, positionId, teamId);
            log.info("Jugador registrado exitosamente: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Error al registrar el jugador: {}", exception.getMessage());
            return new ResponseEntity<>("Error al registrar el jugador", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{playerId}")
    public ResponseEntity<String> putPlayer(@PathVariable("playerId") Integer playerId,
                                            @RequestParam(value = "name") String name,
                                            @RequestParam(value = "profile") MultipartFile profile,
                                            @RequestParam(value = "ci") String ci,
                                            @RequestParam(value = "birthdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthdate,
                                            @RequestParam(value = "positionId") Integer positionId,
                                            @RequestParam(value = "teamId") Integer teamId) {
        log.info("Solicitud para actualizar el jugador: playerId={}, nombre={}, ci={}, birthdate={}, positionId={}, teamId={}",
                playerId, name, ci, birthdate, positionId, teamId);
        try {
            String response = playerService.updateDto(playerId, name, profile, ci, birthdate, positionId, teamId);
            log.info("Jugador actualizado exitosamente: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Error al actualizar el jugador: {}", exception.getMessage());
            return new ResponseEntity<>("Error al actualizar el jugador", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{playerId}")
    public ResponseEntity<String> deleteTeam(@PathVariable("playerId") Integer playerId) {
        log.info("Solicitud para eliminar el jugador con ID: {}", playerId);
        try {
            String response = playerService.deleteByIdDto(playerId);
            log.info("Jugador eliminado exitosamente: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Error al eliminar el jugador: {}", exception.getMessage());
            return new ResponseEntity<>("Error al eliminar el jugador", HttpStatus.BAD_REQUEST);
        }
    }

    // -----CUSTOM CONTROLLER-----
    @GetMapping(path = "/teamId/{teamId}")
    public ResponseEntity<List<PlayerDto>> getPlayersByTeamId(@PathVariable("teamId") Integer teamId) {
        log.info("Solicitud para obtener jugadores por ID de equipo: {}", teamId);
        List<PlayerDto> players = playerService.findAllByTeamIdDto(teamId);
        log.info("Jugadores obtenidos para el equipo {}: {}", teamId, players);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping(path = "/userId/{userId}")
    public ResponseEntity<List<PlayerDto>> getPlayersByUserId(@PathVariable("userId") Integer userId) {
        log.info("Solicitud para obtener jugadores por ID de usuario: {}", userId);
        List<PlayerDto> players = playerService.findAllByUserIdDto(userId);
        log.info("Jugadores obtenidos para el usuario {}: {}", userId, players);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping(path = "/tournamentTeamId/{tournamentTeamId}")
    public ResponseEntity<List<PlayerDto>> getPlayersByTournamentTeamId(@PathVariable("tournamentTeamId") Integer tournamentTeamId) {
        log.info("Solicitud para obtener jugadores por ID de equipo de torneo: {}", tournamentTeamId);
        List<PlayerDto> players = playerService.findAllByTournamentTeamIdDto(tournamentTeamId);
        log.info("Jugadores obtenidos para el equipo de torneo {}: {}", tournamentTeamId, players);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }
}
