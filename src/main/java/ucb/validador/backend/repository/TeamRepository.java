package ucb.validador.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucb.validador.backend.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    List<Team> findAllByUserId(Integer userId);

    List<Team> findAllByTournamentId(Integer tournamentId);

    Team findByTournamentTeamId(Integer tournamentTeamId);

    List<Team> findAllTodayByRefereeId(Integer refereeId);
}