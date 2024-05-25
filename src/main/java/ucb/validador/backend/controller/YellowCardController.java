package ucb.validador.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import ucb.validador.backend.dto.YellowCardDto;
import ucb.validador.backend.service.YellowCardService;

@Slf4j
@RestController
@RequestMapping("/api/yellowcards")
public class YellowCardController {
    private YellowCardService yellowCardService;

    @Autowired
    public YellowCardController(YellowCardService yellowCardService) {
        this.yellowCardService = yellowCardService;
    }

    @GetMapping
    public ResponseEntity<List<YellowCardDto>> getYellowCards() {
        log.info("Solicitud para obtener todas las tarjetas amarillas");
        List<YellowCardDto> yellowCardDtos = yellowCardService.findAllDto();
        log.info("Tarjetas amarillas obtenidas: {}", yellowCardDtos);
        return new ResponseEntity<>(yellowCardDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postYellowCard(@RequestBody YellowCardDto yellowCardDto) {
        log.info("Solicitud para publicar una nueva tarjeta amarilla: {}", yellowCardDto);
        try {
            String response = yellowCardService.saveDto(yellowCardDto);
            log.info("Tarjeta amarilla publicada exitosamente: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Error al publicar la tarjeta amarilla", exception);
            return new ResponseEntity<>("Error al publicar la tarjeta amarilla", HttpStatus.BAD_REQUEST);
        }
    }

    // -----CUSTOM CONTROLLER-----
    @GetMapping(path = "/gameId/{gameId}")
    public ResponseEntity<List<YellowCardDto>> getYellowCardByGameId(@PathVariable("gameId") Integer gameId) {
        log.info("Solicitud para obtener tarjetas amarillas por ID de juego: {}", gameId);
        List<YellowCardDto> yellowCardDtos = yellowCardService.findAllByGameIdDto(gameId);
        log.info("Tarjetas amarillas obtenidas para el juego {}: {}", gameId, yellowCardDtos);
        return new ResponseEntity<>(yellowCardDtos, HttpStatus.OK);
    }
}
