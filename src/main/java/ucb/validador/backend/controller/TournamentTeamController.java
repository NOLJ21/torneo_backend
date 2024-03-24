package ucb.validador.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ucb.validador.backend.dto.TournamentTeamDto;
import ucb.validador.backend.service.TournamentTeamService;

@RestController
@RequestMapping("/api/tournamentsteams")
public class TournamentTeamController {
    private TournamentTeamService tournamentTeamService;

    @Autowired
    public TournamentTeamController(TournamentTeamService tournamentTeamService) {
        this.tournamentTeamService = tournamentTeamService;
    }

    @GetMapping
    public ResponseEntity<List<TournamentTeamDto>> getTournamentsTeams() {
        List<TournamentTeamDto> tournamentsTeams = tournamentTeamService.findAllDto();
        return new ResponseEntity<>(tournamentsTeams, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postTournamentsTeams(@RequestBody TournamentTeamDto tournamentTeamDto) {
        try {
            String response = tournamentTeamService.saveDto(tournamentTeamDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Error posting tournament teams", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/tournamentId/{tournamentId}/teamId/{teamId}")
    public ResponseEntity<String> deleteTeam(@PathVariable("tournamentId") Integer tournamentTeamId,
            @PathVariable("teamId") Integer teamId) {
        try {
            String response = tournamentTeamService.deleteByIdDto(tournamentTeamId, teamId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Error deleting tournament teams", HttpStatus.BAD_REQUEST);
        }
    }

    // -----CUSTOM CONTROLLER-----
    @GetMapping(path = "/tournamentId/{tournamentId}")
    public ResponseEntity<List<TournamentTeamDto>> getTournamentsTeamsByTournamentId(
            @PathVariable("tournamentId") Integer tournamentTeamId) {
        List<TournamentTeamDto> tournamentTeamDtos = tournamentTeamService.findAllByTournamentIdDto(tournamentTeamId);
        return new ResponseEntity<>(tournamentTeamDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/today/refereeId/{refereeId}")
    public ResponseEntity<List<TournamentTeamDto>> getGamesTodayByRefereeId(
            @PathVariable("refereeId") Integer refereeId) {
        List<TournamentTeamDto> tournamentTeamDtos = tournamentTeamService.findAllTodayByRefereeIdDto(refereeId);
        return new ResponseEntity<>(tournamentTeamDtos, HttpStatus.OK);
    }
}
