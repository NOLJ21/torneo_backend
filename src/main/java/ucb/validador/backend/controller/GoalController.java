package ucb.validador.backend.controller;

import java.util.List;

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
        List<GoalDto> goals = goalService.findAllDto();
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postGame(@RequestBody GoalDto goalDto) {
        try {
            String response = goalService.saveDto(goalDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Error posting goal", HttpStatus.BAD_REQUEST);
        }
    }

    // -----CUSTOM CONTROLLER-----
    @GetMapping(path = "/gameId/{gameId}")
    public ResponseEntity<List<GoalDto>> getGoalsByGameId(@PathVariable("gameId") Integer gameId) {
        List<GoalDto> goalDtos = goalService.findAllByGameIdDto(gameId);
        return new ResponseEntity<>(goalDtos, HttpStatus.OK);
    }
}
