package ucb.validador.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ucb.validador.backend.dto.PositionDto;
import ucb.validador.backend.model.Position;
import ucb.validador.backend.repository.PositionRepository;

@Service
public class PositionService {
    private PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<PositionDto> findAllDto() {
        return positionRepository.findAll().stream().map(this::positionToPositionDto).collect(Collectors.toList());
    }

    // ----- MODEL TO DTO -----
    private PositionDto positionToPositionDto(Position position) {
        PositionDto positionDto = new PositionDto(position.getId(), position.getName());
        return positionDto;
    }
}
