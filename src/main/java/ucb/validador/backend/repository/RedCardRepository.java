package ucb.validador.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucb.validador.backend.model.RedCard;

@Repository
public interface RedCardRepository extends JpaRepository<RedCard, Integer> {
    List<RedCard> findByGameIdAndPlayerId(Integer gameId, Integer playerId);

    List<RedCard> findAllByGameId(Integer gameId);
}
