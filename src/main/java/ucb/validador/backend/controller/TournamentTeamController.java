package ucb.validador.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import ucb.validador.backend.dto.TournamentTeamDto;
import ucb.validador.backend.service.TournamentTeamService;

@Slf4j
@RestController
@RequestMapping("/api/tournamentsteams")
public class TournamentTeamController {
    private TournamentTeamService tournamentTeamService;

    @Autowired
    public TournamentTeamController(TournamentTeamService tournamentTeamService) {
        this.tournamentTeamService = tournamentTeamService;
    }

    @GetMapping
    public ResponseEntity<List<TournamentTeamDto>> getTournamentsTeams() {
        log.info("Solicitud para obtener todos los equipos del torneo");
        List<TournamentTeamDto> tournamentsTeams = tournamentTeamService.findAllDto();
        log.info("Equipos del torneo obtenidos: {}", tournamentsTeams);
        return new ResponseEntity<>(tournamentsTeams, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postTournamentsTeams(@RequestBody TournamentTeamDto tournamentTeamDto) {
        log.info("Solicitud para publicar un nuevo equipo de torneo: {}", tournamentTeamDto);
        try {
            String response = tournamentTeamService.saveDto(tournamentTeamDto);
            log.info("Equipo de torneo publicado exitosamente: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Error al publicar el equipo del torneo", exception);
            return new ResponseEntity<>("Error al publicar el equipo del torneo", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/tournamentId/{tournamentId}/teamId/{teamId}")
    public ResponseEntity<String> deleteTeam(@PathVariable("tournamentId") Integer tournamentTeamId,
                                             @PathVariable("teamId") Integer teamId) {
        log.info("Solicitud para eliminar el equipo del torneo: tournamentId={}, teamId={}", tournamentTeamId, teamId);
        try {
            String response = tournamentTeamService.deleteByIdDto(tournamentTeamId, teamId);
            log.info("Equipo del torneo eliminado exitosamente: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Error al eliminar el equipo del torneo", exception);
            return new ResponseEntity<>("Error al eliminar el equipo del torneo", HttpStatus.BAD_REQUEST);
        }
    }

    // -----CUSTOM CONTROLLER-----
    @GetMapping(path = "/tournamentId/{tournamentId}")
    public ResponseEntity<List<TournamentTeamDto>> getTournamentsTeamsByTournamentId(
            @PathVariable("tournamentId") Integer tournamentTeamId) {
        log.info("Solicitud para obtener equipos del torneo por ID de torneo: {}", tournamentTeamId);
        List<TournamentTeamDto> tournamentTeamDtos = tournamentTeamService.findAllByTournamentIdDto(tournamentTeamId);
        log.info("Equipos del torneo obtenidos para el torneo {}: {}", tournamentTeamId, tournamentTeamDtos);
        return new ResponseEntity<>(tournamentTeamDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/today/refereeId/{refereeId}")
    public ResponseEntity<List<TournamentTeamDto>> getGamesTodayByRefereeId(
            @PathVariable("refereeId") Integer refereeId) {
        log.info("Solicitud para obtener equipos del torneo por ID de árbitro: {}", refereeId);
        List<TournamentTeamDto> tournamentTeamDtos = tournamentTeamService.findAllTodayByRefereeIdDto(refereeId);
        log.info("Equipos del torneo obtenidos para el árbitro {}: {}", refereeId, tournamentTeamDtos);
        return new ResponseEntity<>(tournamentTeamDtos, HttpStatus.OK);
    }
}
