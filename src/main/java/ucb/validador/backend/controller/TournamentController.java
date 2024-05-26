package ucb.validador.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import ucb.validador.backend.dto.TournamentDto;
import ucb.validador.backend.service.TournamentService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public ResponseEntity<List<TournamentDto>> getTournaments() {
        log.info("Solicitud para obtener todos los torneos");
        List<TournamentDto> tournamentDtos = tournamentService.findAllDto();
        log.info("Torneos obtenidos: {}", tournamentDtos);
        return new ResponseEntity<>(tournamentDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postTeam(@RequestBody TournamentDto tournamentDto) {
        log.info("Solicitud para publicar un nuevo torneo: {}", tournamentDto);
        try {
            String response = tournamentService.saveDto(tournamentDto);
            log.info("Torneo publicado exitosamente: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Error al publicar el torneo", exception);
            return new ResponseEntity<>("Error al publicar el torneo", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{tournamentId}")
    public ResponseEntity<String> putTeam(@PathVariable("tournamentId") Integer tournamentId,
                                          @RequestBody TournamentDto tournamentDto) {
        log.info("Solicitud para actualizar el torneo: tournamentId={}, tournamentDto={}", tournamentId, tournamentDto);
        try {
            String response = tournamentService.updateDto(tournamentId, tournamentDto);
            log.info("Torneo actualizado exitosamente: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Error al actualizar el torneo", exception);
            return new ResponseEntity<>("Error al actualizar el torneo", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{tournamentId}")
    public ResponseEntity<String> deleteTeam(@PathVariable("tournamentId") Integer tournamentId) {
        log.info("Solicitud para eliminar el torneo: tournamentId={}", tournamentId);
        try {
            String response = tournamentService.deleteByIdDto(tournamentId);
            log.info("Torneo eliminado exitosamente: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Error al eliminar el torneo", exception);
            return new ResponseEntity<>("Error al eliminar el torneo", HttpStatus.BAD_REQUEST);
        }
    }

    // -----CUSTOM CONTROLLER-----
    @GetMapping(path = "/userId/{userId}")
    public ResponseEntity<List<TournamentDto>> getTournamentsByUserId(@PathVariable("userId") Integer userId) {
        log.info("Solicitud para obtener torneos por ID de usuario: {}", userId);
        List<TournamentDto> tournamentDtos = tournamentService.findAllByUserIdDto(userId);
        log.info("Torneos obtenidos para el usuario {}: {}", userId, tournamentDtos);
        return new ResponseEntity<>(tournamentDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/refereeId/{refereeId}")
    public ResponseEntity<List<TournamentDto>> getTournamentsByRefereeId(@PathVariable("refereeId") Integer refereeId) {
        log.info("Solicitud para obtener torneos por ID de árbitro: {}", refereeId);
        List<TournamentDto> tournamentDtos = tournamentService.findAllByRefereeIdDto(refereeId);
        log.info("Torneos obtenidos para el árbitro {}: {}", refereeId, tournamentDtos);
        return new ResponseEntity<>(tournamentDtos, HttpStatus.OK);
    }
}
