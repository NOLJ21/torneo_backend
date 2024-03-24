package ucb.validador.backend.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import ucb.validador.backend.model.TournamentTeam;

@Repository
public interface TournamentTeamRepository extends JpaRepository<TournamentTeam, Integer> {
    @Transactional
    @Modifying
    public int deleteByTournamentIdAndTeamId(Integer tournamentId, Integer teamId);

    List<TournamentTeam> findAllByTournamentId(Integer tournamentId);

    List<TournamentTeam> findAllTodayByRefereeId(Integer refereeId);
}
