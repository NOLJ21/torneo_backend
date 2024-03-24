package ucb.validador.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ucb.validador.backend.dto.TournamentDto;
import ucb.validador.backend.service.TournamentService;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public ResponseEntity<List<TournamentDto>> getTournaments() {
        List<TournamentDto> tournamentDtos = tournamentService.findAllDto();
        return new ResponseEntity<>(tournamentDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postTeam(@RequestBody TournamentDto tournamentDto) {
        try {
            String response = tournamentService.saveDto(tournamentDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Error posting tournament", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{tournamentId}")
    public ResponseEntity<String> putTeam(@PathVariable("tournamentId") Integer tournamentId,
            @RequestBody TournamentDto tournamentDto) {
        try {
            String response = tournamentService.updateDto(tournamentId, tournamentDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Error updating tournament", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{tournamentId}")
    public ResponseEntity<String> deleteTeam(@PathVariable("tournamentId") Integer tournamentId) {
        try {
            String response = tournamentService.deleteByIdDto(tournamentId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Error deleting tournament", HttpStatus.BAD_REQUEST);
        }
    }

    // -----CUSTOM CONTROLLER-----
    @GetMapping(path = "/userId/{userId}")
    public ResponseEntity<List<TournamentDto>> getTournamentsByUserId(@PathVariable("userId") Integer userId) {
        List<TournamentDto> tournamentDtos = tournamentService.findAllByUserIdDto(userId);
        return new ResponseEntity<>(tournamentDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/refereeId/{refereeId}")
    public ResponseEntity<List<TournamentDto>> getTournamentsByRefereeId(@PathVariable("refereeId") Integer refereeId) {
        List<TournamentDto> tournamentDtos = tournamentService.findAllByRefereeIdDto(refereeId);
        return new ResponseEntity<>(tournamentDtos, HttpStatus.OK);
    }
}