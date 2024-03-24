package ucb.validador.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ucb.validador.backend.dto.TournamentDto;
import ucb.validador.backend.model.Tournament;
import ucb.validador.backend.repository.TournamentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    public List<TournamentDto> findAllDto() {
        return tournamentRepository.findAll().stream().map(this::tournamentTournamentDto).collect(Collectors.toList());
    }

    public String saveDto(TournamentDto tournamentDto) {
        Tournament tournament = new Tournament(tournamentDto.getId(), tournamentDto.getName(),
                tournamentDto.getNumber(), tournamentDto.getStart(),
                tournamentDto.getFinish());
        tournamentRepository.save(tournament);
        return "Successfully created tournament";
    }

    public String updateDto(Integer tournamentId, TournamentDto tournamentDto) {
        System.out.println("here");
        Tournament tournamentFound = tournamentRepository.getReferenceById(tournamentId);
        tournamentFound.setName(tournamentDto.getName());
        tournamentFound.setStart(tournamentDto.getStart());
        tournamentFound.setFinish(tournamentDto.getFinish());
        tournamentRepository.save(tournamentFound);
        return "Successfully updated tournament";
    }

    public String deleteByIdDto(Integer tournamentId) {
        Tournament tournamentFound = tournamentRepository.getReferenceById(tournamentId);
        tournamentFound.setStatus(tournamentFound.getStatus() ? false : true);
        tournamentRepository.save(tournamentFound);
        return "Successfully deteled tournament";
    }

    // ----- CUSTOM SERVICES -----
    public List<TournamentDto> findAllByUserIdDto(Integer userId) {
        return tournamentRepository.findAllByUserId(userId).stream().map(this::tournamentTournamentDto)
                .collect(Collectors.toList());
    }

    public List<TournamentDto> findAllByRefereeIdDto(Integer refereeId) {
        return tournamentRepository.findAllByRefereeId(refereeId).stream().map(this::tournamentTournamentDto)
                .collect(Collectors.toList());
    }

    // ----- MODEL TO DTO -----
    private TournamentDto tournamentTournamentDto(Tournament tournament) {
        TournamentDto tournamentDto = new TournamentDto(tournament.getId(), tournament.getName(),
                tournament.getNumber(), tournament.getRound(), tournament.getStart(),
                tournament.getFinish(), tournament.getStatus());
        return tournamentDto;
    }
}