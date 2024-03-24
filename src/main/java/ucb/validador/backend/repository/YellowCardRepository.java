package ucb.validador.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucb.validador.backend.model.YellowCard;

@Repository
public interface YellowCardRepository extends JpaRepository<YellowCard, Integer> {
    List<YellowCard> findByGameIdAndPlayerId(Integer gameId, Integer playerId);

    List<YellowCard> findAllByGameId(Integer gameId);
}
