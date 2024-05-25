package ucb.validador.backend.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import ucb.validador.backend.dto.TeamDto;
import ucb.validador.backend.service.TeamService;

@Slf4j
@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<List<TeamDto>> getTeams() {
        log.info("Solicitud para obtener todos los equipos");
        List<TeamDto> teams = teamService.findAllDto();
        log.info("Equipos obtenidos: {}", teams);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postTeam(@RequestParam(value = "name") String name,
                                           @RequestParam(value = "profile") MultipartFile profile,
                                           @RequestParam(value = "foundation") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate foundation,
                                           @RequestParam(value = "userId") Integer userId) {
        log.info("Solicitud para publicar un nuevo equipo: nombre={}, foundation={}, userId={}", name, foundation, userId);
        try {
            String response = teamService.saveDto(name, profile, foundation, userId);
            log.info("Equipo publicado exitosamente: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Error al publicar el equipo", exception);
            return new ResponseEntity<>("Error al publicar el equipo", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{teamId}")
    public ResponseEntity<String> putTeam(@PathVariable("teamId") Integer teamId,
                                          @RequestParam(value = "name") String name,
                                          @RequestParam(value = "profile") MultipartFile profile,
                                          @RequestParam(value = "foundation") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate foundation) {
        log.info("Solicitud para actualizar el equipo: teamId={}, nombre={}, foundation={}", teamId, name, foundation);
        try {
            String response = teamService.updateDto(teamId, name, profile, foundation);
            log.info("Equipo actualizado exitosamente: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Error al actualizar el equipo", exception);
            return new ResponseEntity<>("Error al actualizar el equipo", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{teamId}")
    public ResponseEntity<String> deleteTeam(@PathVariable("teamId") Integer teamId) {
        log.info("Solicitud para eliminar el equipo: teamId={}", teamId);
        try {
            String response = teamService.deleteByIdDto(teamId);
            log.info("Equipo eliminado exitosamente: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Error al eliminar el equipo", exception);
            return new ResponseEntity<>("Error al eliminar el equipo", HttpStatus.BAD_REQUEST);
        }
    }

    // -----CUSTOM CONTROLLER-----
    @GetMapping(path = "/userId/{userId}")
    public ResponseEntity<List<TeamDto>> getTeamsByUserId(@PathVariable("userId") Integer userId) {
        log.info("Solicitud para obtener equipos por ID de usuario: {}", userId);
        List<TeamDto> teams = teamService.findAllByUserIdDto(userId);
        log.info("Equipos obtenidos para el usuario {}: {}", userId, teams);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping(path = "/tournamentId/{tournamentId}")
    public ResponseEntity<List<TeamDto>> getTeamsByTournamentId(@PathVariable("tournamentId") Integer tournamentId) {
        log.info("Solicitud para obtener equipos por ID del torneo: {}", tournamentId);
        List<TeamDto> teams = teamService.findAllByTournamentIdDto(tournamentId);
        log.info("Equipos obtenidos para el torneo {}: {}", tournamentId, teams);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping(path = "/tournamentTeamId/{tournamentTeamId}")
    public ResponseEntity<TeamDto> getTeamByTournamentTeamId(
            @PathVariable("tournamentTeamId") Integer tournamentTeamId) {
        log.info("Solicitud para obtener equipo por ID del equipo del torneo: {}", tournamentTeamId);
        TeamDto teams = teamService.findAllByTournamentTeamIdDto(tournamentTeamId);
        log.info("Equipo obtenido para el equipo del torneo {}: {}", tournamentTeamId, teams);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping(path = "/today/refereeId/{refereeId}")
    public ResponseEntity<List<TeamDto>> getTeamsTodayByRefereeId(@PathVariable("refereeId") Integer refereeId) {
        log.info("Solicitud para obtener equipos de hoy por ID de árbitro: {}", refereeId);
        List<TeamDto> teamDtos = teamService.findAllTodayByRefereeIdDto(refereeId);
        log.info("Equipos obtenidos para el árbitro {}: {}", refereeId, teamDtos);
        return new ResponseEntity<>(teamDtos, HttpStatus.OK);
    }
}
