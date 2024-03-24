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

import ucb.validador.backend.dto.YellowCardDto;
import ucb.validador.backend.service.YellowCardService;

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
        List<YellowCardDto> yellowCardDtos = yellowCardService.findAllDto();
        return new ResponseEntity<>(yellowCardDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postYellowCard(@RequestBody YellowCardDto yellowCardDto) {
        try {
            String response = yellowCardService.saveDto(yellowCardDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Error posting yellow card", HttpStatus.BAD_REQUEST);
        }
    }

    // -----CUSTOM CONTROLLER-----
    @GetMapping(path = "/gameId/{gameId}")
    public ResponseEntity<List<YellowCardDto>> getYellowCardByGameId(@PathVariable("gameId") Integer gameId) {
        List<YellowCardDto> yellowCardDtos = yellowCardService.findAllByGameIdDto(gameId);
        return new ResponseEntity<>(yellowCardDtos, HttpStatus.OK);
    }
}
