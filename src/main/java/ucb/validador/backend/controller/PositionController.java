package ucb.validador.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ucb.validador.backend.dto.PositionDto;
import ucb.validador.backend.service.PositionService;

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
        List<PositionDto> positionDtos = positionService.findAllDto();
        return new ResponseEntity<>(positionDtos, HttpStatus.OK);
    }
}
