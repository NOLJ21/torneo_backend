package ucb.validador.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucb.validador.backend.model.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

}
