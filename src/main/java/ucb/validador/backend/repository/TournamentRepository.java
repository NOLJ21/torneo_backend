package ucb.validador.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucb.validador.backend.model.Tournament;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {
    List<Tournament> findAllByUserId(Integer userId);

    List<Tournament> findAllByRefereeId(Integer refereeId);
}