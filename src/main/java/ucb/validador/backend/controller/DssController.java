package ucb.validador.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ucb.validador.backend.dto.DssPlayerGoalDto;
import ucb.validador.backend.dto.DssPlayerRedCardDto;
import ucb.validador.backend.dto.DssPlayerYellowCardDto;
import ucb.validador.backend.dto.DssTeamWinnerDto;
import ucb.validador.backend.service.DssService;

@RestController
@RequestMapping("/api/dss")
public class DssController {
    private DssService dssService;

    @Autowired
    public DssController(DssService dssService) {
        this.dssService = dssService;
    }

    @GetMapping(path = "/teamWinnerCount")
    public ResponseEntity<List<DssTeamWinnerDto>> getTeamWinnerCount() {
        List<DssTeamWinnerDto> dssTeamWinnerDtos = dssService.findTeamWinnerCountDto();
        return new ResponseEntity<>(dssTeamWinnerDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/teamWinnerCount/userId/{userId}")
    public ResponseEntity<List<DssTeamWinnerDto>> getTeamWinnerCountByUserId(@PathVariable("userId") Integer userId) {
        List<DssTeamWinnerDto> dssTeamWinnerDtos = dssService.findTeamWinnerCountByUserIdDto(userId);
        return new ResponseEntity<>(dssTeamWinnerDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/playerGoalCount")
    public ResponseEntity<List<DssPlayerGoalDto>> getPlayerGoalCount() {
        List<DssPlayerGoalDto> dssPlayerGoalDtos = dssService.findPlayerGoalCountDto();
        return new ResponseEntity<>(dssPlayerGoalDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/playerGoalCount/userId/{userId}")
    public ResponseEntity<List<DssPlayerGoalDto>> getPlayerGoalCountByUserId(@PathVariable("userId") Integer userId) {
        List<DssPlayerGoalDto> dssPlayerGoalDtos = dssService.findPlayerGoalCountByUserIdDto(userId);
        return new ResponseEntity<>(dssPlayerGoalDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/playerRedCardCount")
    public ResponseEntity<List<DssPlayerRedCardDto>> getPlayerRedCardCount() {
        List<DssPlayerRedCardDto> dssPlayerRedCardDtos = dssService.findPlayerRedCardCountDto();
        return new ResponseEntity<>(dssPlayerRedCardDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/playerRedCardCount/userId/{userId}")
    public ResponseEntity<List<DssPlayerRedCardDto>> getPlayerRedCardCountByUserId(
            @PathVariable("userId") Integer userId) {
        List<DssPlayerRedCardDto> dssPlayerRedCardDtos = dssService.findPlayerRedCardCountByUserIdDto(userId);
        return new ResponseEntity<>(dssPlayerRedCardDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/playerYellowCardCount")
    public ResponseEntity<List<DssPlayerYellowCardDto>> getPlayerYellowCardCount() {
        List<DssPlayerYellowCardDto> dssPlayerYellowCardDtos = dssService.findPlayerYellowCardCountDto();
        return new ResponseEntity<>(dssPlayerYellowCardDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/playerYellowCardCount/userId/{userId}")
    public ResponseEntity<List<DssPlayerYellowCardDto>> getPlayerYellowCardCountByUserId(
            @PathVariable("userId") Integer userId) {
        List<DssPlayerYellowCardDto> dssPlayerYellowCardDtos = dssService.findPlayerYellowCardCountByUserIdDto(userId);
        return new ResponseEntity<>(dssPlayerYellowCardDtos, HttpStatus.OK);
    }
}
