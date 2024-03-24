package ucb.validador.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucb.validador.backend.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    List<Game> findAllByTournamentId(Integer tournamentId);

    List<Game> findAllTodayByRefereeId(Integer refereeId);
}
