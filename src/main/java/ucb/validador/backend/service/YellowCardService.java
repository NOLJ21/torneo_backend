package ucb.validador.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ucb.validador.backend.dto.YellowCardDto;
import ucb.validador.backend.model.Player;
import ucb.validador.backend.model.RedCard;
import ucb.validador.backend.model.YellowCard;
import ucb.validador.backend.repository.PlayerRepository;
import ucb.validador.backend.repository.RedCardRepository;
import ucb.validador.backend.repository.YellowCardRepository;

@Service
public class YellowCardService {
    private YellowCardRepository yellowCardRepository;
    private RedCardRepository redCardRepository;
    private PlayerRepository playerRepository;

    @Autowired
    public YellowCardService(YellowCardRepository yellowCardRepository, RedCardRepository redCardRepository,
            PlayerRepository playerRepository) {
        this.yellowCardRepository = yellowCardRepository;
        this.redCardRepository = redCardRepository;
        this.playerRepository = playerRepository;
    }

    public List<YellowCardDto> findAllDto() {
        return yellowCardRepository.findAll().stream().map(this::yellowCardToYellowCardDto)
                .collect(Collectors.toList());
    }

    public String saveDto(YellowCardDto yellowCardDto) {
        List<YellowCard> foundYellowCards = yellowCardRepository.findByGameIdAndPlayerId(yellowCardDto.getGameId(),
                yellowCardDto.getPlayerId());
        if (foundYellowCards.isEmpty()) {
            YellowCard yellowCard = new YellowCard(yellowCardDto.getId(), yellowCardDto.getGameId(),
                    yellowCardDto.getPlayerId());
            yellowCardRepository.save(yellowCard);
            return "Successfully created yellow card";
        } else if (foundYellowCards.size() <= 1) {
            YellowCard yellowCard = new YellowCard(yellowCardDto.getId(), yellowCardDto.getGameId(),
                    yellowCardDto.getPlayerId());
            yellowCardRepository.save(yellowCard);
            RedCard redCard = new RedCard(yellowCardDto.getId(), yellowCardDto.getGameId(),
                    yellowCardDto.getPlayerId());
            redCardRepository.save(redCard);
            Player playerFound = playerRepository.getReferenceById(yellowCardDto.getPlayerId());
            playerFound.setStatus(false);
            playerRepository.save(playerFound);
            return "Successfully created second yellow card, first red card and suspended player";
        } else {
            return "Can not create yellow card";
        }
    }

    public List<YellowCardDto> findAllByGameIdDto(Integer gameId) {
        return yellowCardRepository.findAllByGameId(gameId).stream().map(this::yellowCardToYellowCardDto)
                .collect(Collectors.toList());
    }

    // ----- MODEL TO DTO -----
    private YellowCardDto yellowCardToYellowCardDto(YellowCard yellowCard) {
        YellowCardDto yellowCardDto = new YellowCardDto(yellowCard.getId(), yellowCard.getGameId(),
                yellowCard.getPlayerId(), yellowCard.getYellowCard(),
                yellowCard.getTime(),
                yellowCard.getStatus());
        return yellowCardDto;
    }
}
