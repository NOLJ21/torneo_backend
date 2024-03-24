package ucb.validador.backend.repository;

import org.springframework.stereotype.Repository;
import ucb.validador.backend.model.Player;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    List<Player> findAllByTeamId(Integer teamId);

    List<Player> findAllByUserId(Integer userId);

    List<Player> findAllByTournamentTeamId(Integer tournamentTeamId);
}
