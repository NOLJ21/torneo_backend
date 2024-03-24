package ucb.validador.backend.dto;

import java.time.LocalDate;

public class GameDto {
    private Integer id;
    private Integer tournamentId;
    private Integer tournamentTeamAId;
    private Integer tournamentTeamBId;
    private Integer userId;
    private LocalDate gameDate;
    private Integer round;
    private Integer tournamentTeamWinnerId;
    private Double latitude;
    private Double longitude;
    private Boolean status = true;

    public GameDto(Integer id, Integer tournamentId, Integer tournamentTeamAId, Integer tournamentTeamBId,
            Integer userId, LocalDate gameDate, Integer round, Integer tournamentTeamWinnerId, Double latitude,
            Double longitude, Boolean status) {
        this.id = id;
        this.tournamentId = tournamentId;
        this.tournamentTeamAId = tournamentTeamAId;
        this.tournamentTeamBId = tournamentTeamBId;
        this.userId = userId;
        this.gameDate = gameDate;
        this.round = round;
        this.tournamentTeamWinnerId = tournamentTeamWinnerId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Integer tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Integer getTournamentTeamAId() {
        return tournamentTeamAId;
    }

    public void setTournamentTeamAId(Integer tournamentTeamAId) {
        this.tournamentTeamAId = tournamentTeamAId;
    }

    public Integer getTournamentTeamBId() {
        return tournamentTeamBId;
    }

    public void setTournamentTeamBId(Integer tournamentTeamBId) {
        this.tournamentTeamBId = tournamentTeamBId;
    }

    public LocalDate getGameDate() {
        return gameDate;
    }

    public void setGameDate(LocalDate gameDate) {
        this.gameDate = gameDate;
    }

    public Integer getTournamentTeamWinnerId() {
        return tournamentTeamWinnerId;
    }

    public void setTournamentTeamWinnerId(Integer tournamentTeamWinnerId) {
        this.tournamentTeamWinnerId = tournamentTeamWinnerId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "GameDto [id=" + id + ", tournamentId=" + tournamentId + ", tournamentTeamAId=" + tournamentTeamAId
                + ", tournamentTeamBId=" + tournamentTeamBId + ", userId=" + userId + ", gameDate=" + gameDate
                + ", round=" + round + ", tournamentTeamWinnerId=" + tournamentTeamWinnerId + ", latitude=" + latitude
                + ", longitude=" + longitude + ", status=" + status + "]";
    }

}
