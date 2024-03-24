package ucb.validador.backend.dto;

import java.time.LocalTime;

public class YellowCardDto {
    private Integer id;
    private Integer gameId;
    private Integer playerId;
    private Integer yellowCard = 1;
    private LocalTime time = LocalTime.now();
    private Boolean status = true;

    public YellowCardDto(Integer id, Integer gameId, Integer playerId, Integer yellowCard, LocalTime time,
            Boolean status) {
        this.id = id;
        this.gameId = gameId;
        this.playerId = playerId;
        this.yellowCard = yellowCard;
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

    public Integer getYellowCard() {
        return yellowCard;
    }

    public void setYellowCard(Integer yellowCard) {
        this.yellowCard = yellowCard;
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
        return "YellowCardDto [id=" + id + ", gameId=" + gameId + ", playerId=" + playerId + ", yellowCard="
                + yellowCard + ", time=" + time + ", status=" + status + "]";
    }
}
