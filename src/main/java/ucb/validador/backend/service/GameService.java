package ucb.validador.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ucb.validador.backend.dto.GameDto;
import ucb.validador.backend.model.Game;
import ucb.validador.backend.repository.GameRepository;

@Service
public class GameService {
    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameDto> findAllDto() {
        return gameRepository.findAll().stream().map(this::gameToGameDto).collect(Collectors.toList());
    }

    public String saveDto(GameDto gameDto) {
        Game game = new Game(gameDto.getId(), gameDto.getTournamentId(), gameDto.getTournamentTeamAId(),
                gameDto.getTournamentTeamBId(), gameDto.getUserId(),
                gameDto.getGameDate(), gameDto.getRound(), gameDto.getLatitude(), gameDto.getLongitude());
        gameRepository.save(game);
        return "Successfully created game";
    }

    public String deleteByIdDto(Integer gameId) {
        gameRepository.deleteById(gameId);
        return "Successfully deteled game";
    }

    // ----- CUSTOM SERVICES -----
    public List<GameDto> findAllByTournamentIdDto(Integer tournamentId) {
        return gameRepository.findAllByTournamentId(tournamentId).stream().map(this::gameToGameDto)
                .collect(Collectors.toList());
    }

    public List<GameDto> findAllTodayByRefereeIdDto(Integer refereeId) {
        return gameRepository.findAllTodayByRefereeId(refereeId).stream().map(this::gameToGameDto)
                .collect(Collectors.toList());
    }

    // ----- MODEL TO DTO -----
    private GameDto gameToGameDto(Game game) {
        GameDto gameDto = new GameDto(game.getId(), game.getTournamentId(), game.getTournamentTeamAId(),
                game.getTournamentTeamBId(), game.getUserId(), game.getGameDate(), game.getRound(),
                game.getTournamentTeamWinnerId(),
                game.getLatitude(),
                game.getLongitude(),
                game.getStatus());
        return gameDto;
    }
}
