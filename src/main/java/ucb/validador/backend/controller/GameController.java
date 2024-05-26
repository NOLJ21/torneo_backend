package ucb.validador.backend.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ucb.validador.backend.dto.GameDto;
import ucb.validador.backend.service.GameService;

@Slf4j
@RestController
@RequestMapping("/api/games")
public class GameController {
    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<GameDto>> getGames() {
        log.info("Solicitud para obtener todos los juegos");
        List<GameDto> games = gameService.findAllDto();
        log.info("Juegos obtenidos: {}", games);
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postGame(@RequestBody GameDto gameDto) {
        log.info("Solicitud para registrar un nuevo juego: {}", gameDto);
        try {
            String response = gameService.saveDto(gameDto);
            log.info("Juego registrado exitosamente: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Error al registrar el juego: {}", exception.getMessage());
            return new ResponseEntity<>("Error al registrar el juego", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{gameId}")
    public ResponseEntity<String> deleteGame(@PathVariable("gameId") Integer gameId) {
        log.info("Solicitud para eliminar el juego con ID: {}", gameId);
        try {
            String response = gameService.deleteByIdDto(gameId);
            log.info("Juego eliminado exitosamente: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Error al eliminar el juego: {}", exception.getMessage());
            return new ResponseEntity<>("Error al eliminar el juego", HttpStatus.BAD_REQUEST);
        }
    }

    // -----CUSTOM CONTROLLER-----
    @GetMapping(path = "/tournamentId/{tournamentId}")
    public ResponseEntity<List<GameDto>> getGamesByTournamentId(@PathVariable("tournamentId") Integer tournamentId) {
        log.info("Solicitud para obtener juegos por ID de torneo: {}", tournamentId);
        List<GameDto> games = gameService.findAllByTournamentIdDto(tournamentId);
        log.info("Juegos obtenidos para el torneo {}: {}", tournamentId, games);
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @GetMapping(path = "/today/refereeId/{refereeId}")
    public ResponseEntity<List<GameDto>> getGamesTodayByRefereeId(@PathVariable("refereeId") Integer refereeId) {
        log.info("Solicitud para obtener juegos de hoy por ID de árbitro: {}", refereeId);
        List<GameDto> games = gameService.findAllTodayByRefereeIdDto(refereeId);
        log.info("Juegos de hoy obtenidos para el árbitro {}: {}", refereeId, games);
        return new ResponseEntity<>(games, HttpStatus.OK);
    }
}
