package ucb.validador.backend.dto;

import java.time.LocalTime;

public class GoalDto {
    private Integer id;
    private Integer gameId;
    private Integer playerId;
    private Integer goal;
    private LocalTime time;
    private Boolean status;

    public GoalDto(Integer id, Integer gameId, Integer playerId, Integer goal, LocalTime time, Boolean status) {
        this.id = id;
        this.gameId = gameId;
        this.playerId = playerId;
        this.goal = goal;
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

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "GoalDto [id=" + id + ", gameId=" + gameId + ", playerId=" + playerId + ", goal=" + goal + ", time="
                + time + ", status=" + status + "]";
    }
}
