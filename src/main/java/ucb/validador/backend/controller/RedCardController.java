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

import ucb.validador.backend.dto.RedCardDto;
import ucb.validador.backend.service.RedCardService;

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
        List<RedCardDto> redCardDtos = redCardService.findAllDto();
        return new ResponseEntity<>(redCardDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postRedCard(@RequestBody RedCardDto redCardDto) {
        try {
            String response = redCardService.saveDto(redCardDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Error posting red card", HttpStatus.BAD_REQUEST);
        }
    }

    // -----CUSTOM CONTROLLER-----
    @GetMapping(path = "/gameId/{gameId}")
    public ResponseEntity<List<RedCardDto>> getRedCardByGameId(@PathVariable("gameId") Integer gameId) {
        List<RedCardDto> redCardDtos = redCardService.findAllByGameIdDto(gameId);
        return new ResponseEntity<>(redCardDtos, HttpStatus.OK);
    }
}
