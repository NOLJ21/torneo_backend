package ucb.validador.backend.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ucb.validador.backend.dto.GoalDto;
import ucb.validador.backend.service.GoalService;

@Slf4j
@RestController
@RequestMapping("/api/goals")
public class GoalController {
    private GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping
    public ResponseEntity<List<GoalDto>> getGoals() {
        log.info("Solicitud para obtener todos los goles");
        List<GoalDto> goals = goalService.findAllDto();
        log.info("Goles obtenidos: {}", goals);
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postGame(@RequestBody GoalDto goalDto) {
        log.info("Solicitud para registrar un nuevo gol: {}", goalDto);
        try {
            String response = goalService.saveDto(goalDto);
            log.info("Gol registrado exitosamente: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Error al registrar el gol: {}", exception.getMessage());
            return new ResponseEntity<>("Error al registrar el gol", HttpStatus.BAD_REQUEST);
        }
    }

    // -----CUSTOM CONTROLLER-----
    @GetMapping(path = "/gameId/{gameId}")
    public ResponseEntity<List<GoalDto>> getGoalsByGameId(@PathVariable("gameId") Integer gameId) {
        log.info("Solicitud para obtener goles por ID de juego: {}", gameId);
        List<GoalDto> goalDtos = goalService.findAllByGameIdDto(gameId);
        log.info("Goles obtenidos para el juego {}: {}", gameId, goalDtos);
        return new ResponseEntity<>(goalDtos, HttpStatus.OK);
    }
}
