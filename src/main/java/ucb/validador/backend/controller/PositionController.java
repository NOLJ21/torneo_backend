package ucb.validador.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ucb.validador.backend.dto.PositionDto;
import ucb.validador.backend.service.PositionService;

@Slf4j
@RestController
@RequestMapping("/api/positions")
public class PositionController {
    private PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public ResponseEntity<List<PositionDto>> getPositions() {
        log.info("Solicitud para obtener todas las posiciones");
        List<PositionDto> positionDtos = positionService.findAllDto();
        log.info("Posiciones obtenidas: {}", positionDtos);
        return new ResponseEntity<>(positionDtos, HttpStatus.OK);
    }
}
