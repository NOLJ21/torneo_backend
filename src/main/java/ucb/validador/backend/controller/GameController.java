package ucb.validador.backend.controller;

import java.util.List;

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
        List<GameDto> games = gameService.findAllDto();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postGame(@RequestBody GameDto gameDto) {
        try {
            String response = gameService.saveDto(gameDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Error posting game", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{gameId}")
    public ResponseEntity<String> deleteGame(@PathVariable("gameId") Integer gameId) {
        try {
            String response = gameService.deleteByIdDto(gameId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Error deleting game", HttpStatus.BAD_REQUEST);
        }
    }

    // -----CUSTOM CONTROLLER-----
    @GetMapping(path = "/tournamentId/{tournamentId}")
    public ResponseEntity<List<GameDto>> getGamesByTournamentId(@PathVariable("tournamentId") Integer tournamentId) {
        List<GameDto> games = gameService.findAllByTournamentIdDto(tournamentId);
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @GetMapping(path = "/today/refereeId/{refereeId}")
    public ResponseEntity<List<GameDto>> getGamesTodayByRefereeId(@PathVariable("refereeId") Integer refereeId) {
        List<GameDto> games = gameService.findAllTodayByRefereeIdDto(refereeId);
        return new ResponseEntity<>(games, HttpStatus.OK);
    }
}
