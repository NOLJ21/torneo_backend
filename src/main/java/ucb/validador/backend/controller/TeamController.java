package ucb.validador.backend.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ucb.validador.backend.dto.TeamDto;
import ucb.validador.backend.service.TeamService;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<List<TeamDto>> getTeams() {
        List<TeamDto> teams = teamService.findAllDto();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postTeam(@RequestParam(value = "name") String name,
            @RequestParam(value = "profile") MultipartFile profile,
            @RequestParam(value = "foundation") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate foundation,
            @RequestParam(value = "userId") Integer userId) {
        try {
            String response = teamService.saveDto(name, profile, foundation, userId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Error posting team", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{teamId}")
    public ResponseEntity<String> putTeam(@PathVariable("teamId") Integer teamId,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "profile") MultipartFile profile,
            @RequestParam(value = "foundation") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate foundation) {
        try {
            String response = teamService.updateDto(teamId, name, profile, foundation);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Error updating team", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{teamId}")
    public ResponseEntity<String> deleteTeam(@PathVariable("teamId") Integer teamId) {
        try {
            String response = teamService.deleteByIdDto(teamId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Error deleting team", HttpStatus.BAD_REQUEST);
        }
    }

    // -----CUSTOM CONTROLLER-----
    @GetMapping(path = "/userId/{userId}")
    public ResponseEntity<List<TeamDto>> getTeamsByUserId(@PathVariable("userId") Integer userId) {
        List<TeamDto> teams = teamService.findAllByUserIdDto(userId);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping(path = "/tournamentId/{tournamentId}")
    public ResponseEntity<List<TeamDto>> getTeamsByTournamentId(@PathVariable("tournamentId") Integer tournamentId) {
        List<TeamDto> teams = teamService.findAllByTournamentIdDto(tournamentId);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping(path = "/tournamentTeamId/{tournamentTeamId}")
    public ResponseEntity<TeamDto> getTeamByTournamentTeamId(
            @PathVariable("tournamentTeamId") Integer tournamentTeamId) {
        TeamDto teams = teamService.findAllByTournamentTeamIdDto(tournamentTeamId);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping(path = "/today/refereeId/{refereeId}")
    public ResponseEntity<List<TeamDto>> getTeamsTodayByRefereeId(@PathVariable("refereeId") Integer refereeId) {
        List<TeamDto> teamDtos = teamService.findAllTodayByRefereeIdDto(refereeId);
        return new ResponseEntity<>(teamDtos, HttpStatus.OK);
    }
}