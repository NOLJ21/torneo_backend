package ucb.validador.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ucb.validador.backend.dto.RedCardDto;
import ucb.validador.backend.service.RedCardService;

@Slf4j
@RestController
@RequestMapping("/api/redcards")
public class RedCardController {
    private RedCardService redCardService;

    @Autowired
    public RedCardController(RedCardService redCardService) {
        this.redCardService = redCardService;
    }

    @GetMapping
    public ResponseEntity<List<RedCardDto>> getRedCards() {
        log.info("Solicitud para obtener todas las tarjetas rojas");
        List<RedCardDto> redCardDtos = redCardService.findAllDto();
        log.info("Tarjetas rojas obtenidas: {}", redCardDtos);
        return new ResponseEntity<>(redCardDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postRedCard(@RequestBody RedCardDto redCardDto) {
        log.info("Solicitud para publicar una nueva tarjeta roja: {}", redCardDto);
        try {
            String response = redCardService.saveDto(redCardDto);
            log.info("Tarjeta roja publicada exitosamente: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Error al publicar la tarjeta roja", exception);
            return new ResponseEntity<>("Error al publicar la tarjeta roja", HttpStatus.BAD_REQUEST);
        }
    }

    // -----CUSTOM CONTROLLER-----
    @GetMapping(path = "/gameId/{gameId}")
    public ResponseEntity<List<RedCardDto>> getRedCardByGameId(@PathVariable("gameId") Integer gameId) {
        log.info("Solicitud para obtener tarjetas rojas por ID del juego: {}", gameId);
        List<RedCardDto> redCardDtos = redCardService.findAllByGameIdDto(gameId);
        log.info("Tarjetas rojas obtenidas para el juego {}: {}", gameId, redCardDtos);
        return new ResponseEntity<>(redCardDtos, HttpStatus.OK);
    }
}
