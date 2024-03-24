package ucb.validador.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucb.validador.backend.model.Goal;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Integer> {
    List<Goal> findByGameIdAndPlayerId(Integer gameId, Integer playerId);

    List<Goal> findAllByGameId(Integer gameId);
}
