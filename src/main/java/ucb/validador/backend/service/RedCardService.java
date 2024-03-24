package ucb.validador.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ucb.validador.backend.dto.RedCardDto;
import ucb.validador.backend.model.Player;
import ucb.validador.backend.model.RedCard;
import ucb.validador.backend.repository.PlayerRepository;
import ucb.validador.backend.repository.RedCardRepository;

@Service
public class RedCardService {
    private RedCardRepository redCardRepository;
    private PlayerRepository playerRepository;

    @Autowired
    public RedCardService(RedCardRepository redCardRepository, PlayerRepository playerRepository) {
        this.redCardRepository = redCardRepository;
        this.playerRepository = playerRepository;
    }

    public List<RedCardDto> findAllDto() {
        return redCardRepository.findAll().stream().map(this::redCardToRedCardDto)
                .collect(Collectors.toList());
    }

    public String saveDto(RedCardDto redCardDto) {
        List<RedCard> foundRedCards = redCardRepository.findByGameIdAndPlayerId(redCardDto.getGameId(),
                redCardDto.getPlayerId());
        if (foundRedCards.isEmpty()) {
            RedCard redCard = new RedCard(redCardDto.getId(), redCardDto.getGameId(),
                    redCardDto.getPlayerId());
            redCardRepository.save(redCard);
            Player playerFound = playerRepository.getReferenceById(redCardDto.getPlayerId());
            playerFound.setStatus(false);
            playerRepository.save(playerFound);
            return "Successfully created red card and suspende player";
        } else {
            return "Can not create red card";
        }
    }

    public List<RedCardDto> findAllByGameIdDto(Integer gameId) {
        return redCardRepository.findAllByGameId(gameId).stream().map(this::redCardToRedCardDto)
                .collect(Collectors.toList());
    }

    // ----- MODEL TO DTO -----
    private RedCardDto redCardToRedCardDto(RedCard redCard) {
        RedCardDto redCardDto = new RedCardDto(redCard.getId(), redCard.getGameId(),
                redCard.getPlayerId(), redCard.getRedCard(),
                redCard.getTime(),
                redCard.getStatus());
        return redCardDto;
    }
}
