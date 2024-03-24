package ucb.validador.backend.dto;

import java.time.LocalTime;

public class RedCardDto {
    private Integer id;
    private Integer gameId;
    private Integer playerId;
    private Integer redCard = 1;
    private LocalTime time = LocalTime.now();
    private Boolean status = true;

    public RedCardDto(Integer id, Integer gameId, Integer playerId, Integer redCard, LocalTime time, Boolean status) {
        this.id = id;
        this.gameId = gameId;
        this.playerId = playerId;
        this.redCard = redCard;
        this.time = time;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getRedCard() {
        return redCard;
    }

    public void setRedCard(Integer redCard) {
        this.redCard = redCard;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RedCardDto [id=" + id + ", gameId=" + gameId + ", playerId=" + playerId + ", redCard=" + redCard
                + ", time=" + time + ", status=" + status + "]";
    }
}
