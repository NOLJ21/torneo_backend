package ucb.validador.backend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ucb.validador.backend.dto.TeamDto;
import ucb.validador.backend.model.Team;
import ucb.validador.backend.repository.TeamRepository;
//import ucb.validador.backend.s3.FileService;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;
//    private FileService fileService;

    @Autowired
    private FileService fileService;

    public List<TeamDto> findAllDto() {
        return teamRepository.findAll().stream().map(this::teamToTeamDto).collect(Collectors.toList());
    }

    public String saveDto(String name, MultipartFile profile, LocalDate foundation, Integer userId) {
//        String imageName = fileService.uploadFile(profile);

        String imageName = fileService.uploadFile(profile);
        Team team = new Team(null, name, imageName, foundation, userId);
        teamRepository.save(team);
        return "Successfully created team";
    }

    public String updateDto(Integer teamId, String name, MultipartFile profile, LocalDate foundation) {
//        String imageName = fileService.uploadFile(profile);
        String imageName = fileService.uploadFile(profile);
        Team teamFound = teamRepository.getReferenceById(teamId);
        teamFound.setName(name);
        teamFound.setProfile(imageName);
        teamFound.setFoundation(foundation);
        teamRepository.save(teamFound);
        return "Successfully updated team";
    }

    public String deleteByIdDto(Integer teamId) {
        Team teamFound = teamRepository.getReferenceById(teamId);
        teamFound.setStatus(teamFound.getStatus() ? false : true);
        teamRepository.save(teamFound);
        return "Successfully deteled team";
    }

    // ----- CUSTOM SERVICES -----
    public List<TeamDto> findAllByUserIdDto(Integer userId) {
        return teamRepository.findAllByUserId(userId).stream().map(this::teamToTeamDto).collect(Collectors.toList());
    }

    public List<TeamDto> findAllByTournamentIdDto(Integer userId) {
        return teamRepository.findAllByTournamentId(userId).stream().map(this::teamToTeamDto)
                .collect(Collectors.toList());
    }

    public TeamDto findAllByTournamentTeamIdDto(Integer tournamentTeamId) {
        return teamToTeamDto(teamRepository.findByTournamentTeamId(tournamentTeamId));
    }

    public List<TeamDto> findAllTodayByRefereeIdDto(Integer refereeId) {
        return teamRepository.findAllTodayByRefereeId(refereeId).stream().map(this::teamToTeamDto)
                .collect(Collectors.toList());
    }

    // ----- MODEL TO DTO -----
    private TeamDto teamToTeamDto(Team team) {
        String profile = fileService.getDownloadUrl(team.getProfile());
        TeamDto teamDto = new TeamDto(team.getId(), team.getName(), profile, team.getFoundation(),
                team.getUserId(),
                team.getStatus());
        return teamDto;
    }
}
