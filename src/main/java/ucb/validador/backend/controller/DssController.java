package ucb.validador.backend.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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
        log.info("Solicitud para obtener el conteo de equipos ganadores");
        List<DssTeamWinnerDto> dssTeamWinnerDtos = dssService.findTeamWinnerCountDto();
        log.info("Conteo de equipos ganadores obtenido: {}", dssTeamWinnerDtos);
        return new ResponseEntity<>(dssTeamWinnerDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/teamWinnerCount/userId/{userId}")
    public ResponseEntity<List<DssTeamWinnerDto>> getTeamWinnerCountByUserId(@PathVariable("userId") Integer userId) {
        log.info("Solicitud para obtener el conteo de equipos ganadores por ID de usuario: {}", userId);
        List<DssTeamWinnerDto> dssTeamWinnerDtos = dssService.findTeamWinnerCountByUserIdDto(userId);
        log.info("Conteo de equipos ganadores por ID de usuario {} obtenido: {}", userId, dssTeamWinnerDtos);
        return new ResponseEntity<>(dssTeamWinnerDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/playerGoalCount")
    public ResponseEntity<List<DssPlayerGoalDto>> getPlayerGoalCount() {
        log.info("Solicitud para obtener el conteo de goles por jugador");
        List<DssPlayerGoalDto> dssPlayerGoalDtos = dssService.findPlayerGoalCountDto();
        log.info("Conteo de goles por jugador obtenido: {}", dssPlayerGoalDtos);
        return new ResponseEntity<>(dssPlayerGoalDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/playerGoalCount/userId/{userId}")
    public ResponseEntity<List<DssPlayerGoalDto>> getPlayerGoalCountByUserId(@PathVariable("userId") Integer userId) {
        log.info("Solicitud para obtener el conteo de goles por jugador y ID de usuario: {}", userId);
        List<DssPlayerGoalDto> dssPlayerGoalDtos = dssService.findPlayerGoalCountByUserIdDto(userId);
        log.info("Conteo de goles por jugador y ID de usuario {} obtenido: {}", userId, dssPlayerGoalDtos);
        return new ResponseEntity<>(dssPlayerGoalDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/playerRedCardCount")
    public ResponseEntity<List<DssPlayerRedCardDto>> getPlayerRedCardCount() {
        log.info("Solicitud para obtener el conteo de tarjetas rojas por jugador");
        List<DssPlayerRedCardDto> dssPlayerRedCardDtos = dssService.findPlayerRedCardCountDto();
        log.info("Conteo de tarjetas rojas por jugador obtenido: {}", dssPlayerRedCardDtos);
        return new ResponseEntity<>(dssPlayerRedCardDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/playerRedCardCount/userId/{userId}")
    public ResponseEntity<List<DssPlayerRedCardDto>> getPlayerRedCardCountByUserId(
            @PathVariable("userId") Integer userId) {
        log.info("Solicitud para obtener el conteo de tarjetas rojas por jugador y ID de usuario: {}", userId);
        List<DssPlayerRedCardDto> dssPlayerRedCardDtos = dssService.findPlayerRedCardCountByUserIdDto(userId);
        log.info("Conteo de tarjetas rojas por jugador y ID de usuario {} obtenido: {}", userId, dssPlayerRedCardDtos);
        return new ResponseEntity<>(dssPlayerRedCardDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/playerYellowCardCount")
    public ResponseEntity<List<DssPlayerYellowCardDto>> getPlayerYellowCardCount() {
        log.info("Solicitud para obtener el conteo de tarjetas amarillas por jugador");
        List<DssPlayerYellowCardDto> dssPlayerYellowCardDtos = dssService.findPlayerYellowCardCountDto();
        log.info("Conteo de tarjetas amarillas por jugador obtenido: {}", dssPlayerYellowCardDtos);
        return new ResponseEntity<>(dssPlayerYellowCardDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/playerYellowCardCount/userId/{userId}")
    public ResponseEntity<List<DssPlayerYellowCardDto>> getPlayerYellowCardCountByUserId(
            @PathVariable("userId") Integer userId) {
        log.info("Solicitud para obtener el conteo de tarjetas amarillas por jugador y ID de usuario: {}", userId);
        List<DssPlayerYellowCardDto> dssPlayerYellowCardDtos = dssService.findPlayerYellowCardCountByUserIdDto(userId);
        log.info("Conteo de tarjetas amarillas por jugador y ID de usuario {} obtenido: {}", userId, dssPlayerYellowCardDtos);
        return new ResponseEntity<>(dssPlayerYellowCardDtos, HttpStatus.OK);
    }
}
