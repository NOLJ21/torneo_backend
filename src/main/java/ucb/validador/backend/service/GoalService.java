package ucb.validador.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ucb.validador.backend.dto.GoalDto;
import ucb.validador.backend.model.Goal;
import ucb.validador.backend.repository.GoalRepository;

@Service
public class GoalService {
    private GoalRepository goalRepository;

    @Autowired
    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public List<GoalDto> findAllDto() {
        return goalRepository.findAll().stream().map(this::goalToGoalDto).collect(Collectors.toList());
    }

    public String saveDto(GoalDto goalDto) {
        List<Goal> foundGoals = goalRepository.findByGameIdAndPlayerId(goalDto.getGameId(), goalDto.getPlayerId());
        if (foundGoals.isEmpty()) {
            Goal goal = new Goal(goalDto.getId(), goalDto.getGameId(), goalDto.getPlayerId());
            goalRepository.save(goal);
            return "Successfully created goal";
        } else {
            Goal goal = foundGoals.get(0);
            goal.setGoal(foundGoals.get(0).getGoal() + 1);
            goalRepository.save(goal);
            return "Successfully updated goal";
        }
    }

    public List<GoalDto> findAllByGameIdDto(Integer gameId) {
        return goalRepository.findAllByGameId(gameId).stream().map(this::goalToGoalDto).collect(Collectors.toList());
    }

    // ----- MODEL TO DTO -----
    private GoalDto goalToGoalDto(Goal goal) {
        GoalDto goalDto = new GoalDto(goal.getId(), goal.getGameId(), goal.getPlayerId(), goal.getGoal(),
                goal.getTime(),
                goal.getStatus());
        return goalDto;
    }
}
