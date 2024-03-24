package ucb.validador.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ucb.validador.backend.dto.TournamentTeamDto;
import ucb.validador.backend.model.TournamentTeam;
import ucb.validador.backend.repository.TournamentTeamRepository;

@Service
public class TournamentTeamService {
    private TournamentTeamRepository tournamentTeamRepository;

    @Autowired
    public TournamentTeamService(TournamentTeamRepository tournamentTeamRepository) {
        this.tournamentTeamRepository = tournamentTeamRepository;
    }

    public List<TournamentTeamDto> findAllDto() {
        return tournamentTeamRepository.findAll().stream().map(this::tournamentTeamToTournamentTeamDto)
                .collect(Collectors.toList());
    }

    public String saveDto(TournamentTeamDto tournamentTeamDto) {
        TournamentTeam tournamentTeam = new TournamentTeam(tournamentTeamDto.getId(),
                tournamentTeamDto.getTournamentId(), tournamentTeamDto.getTeamId());
        tournamentTeamRepository.save(tournamentTeam);
        return "Successfully created tournament team";
    }

    public String deleteByIdDto(Integer tournamentId, Integer teamId) {
        tournamentTeamRepository.deleteByTournamentIdAndTeamId(tournamentId, teamId);
        return "Successfully deteled tournament team";
    }

    // ----- CUSTOM SERVICES -----
    public List<TournamentTeamDto> findAllByTournamentIdDto(Integer tournamentId) {
        return tournamentTeamRepository.findAllByTournamentId(tournamentId).stream()
                .map(this::tournamentTeamToTournamentTeamDto)
                .collect(Collectors.toList());
    }

    public List<TournamentTeamDto> findAllTodayByRefereeIdDto(Integer refereeId) {
        return tournamentTeamRepository.findAllTodayByRefereeId(refereeId).stream()
                .map(this::tournamentTeamToTournamentTeamDto)
                .collect(Collectors.toList());
    }

    // ----- MODEL TO DTO -----
    private TournamentTeamDto tournamentTeamToTournamentTeamDto(TournamentTeam tournamentTeam) {
        TournamentTeamDto tournamentTeamDto = new TournamentTeamDto(tournamentTeam.getId(),
                tournamentTeam.getTournamentId(), tournamentTeam.getTeamId(), tournamentTeam.getStatus());
        return tournamentTeamDto;
    }
}
