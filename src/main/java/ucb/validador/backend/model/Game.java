package ucb.validador.backend.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "games")
@NamedQueries({
        @NamedQuery(name = "Game.findAllTodayByRefereeId", query = "SELECT ga FROM Game ga WHERE ga.gameDate = CURRENT_DATE AND ga.userId = :refereeId") })

public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer tournamentId;
    @Column(name = "tournament_team_a_id")
    private Integer tournamentTeamAId;
    @Column(name = "tournament_team_b_id")
    private Integer tournamentTeamBId;
    private Integer userId;
    private LocalDate gameDate;
    private Integer round;
    @Column(name = "tournament_team_winner_id")
    private Integer tournamentTeamWinnerId;
    private Double latitude;
    private Double longitude;
    private Boolean status = true;

    public Game() {
    }

    public Game(Integer id, Integer tournamentId, Integer tournamentTeamAId, Integer tournamentTeamBId, Integer userId,
            LocalDate gameDate, Integer round, Double latitude, Double longitude) {
        this.id = id;
        this.tournamentId = tournamentId;
        this.tournamentTeamAId = tournamentTeamAId;
        this.tournamentTeamBId = tournamentTeamBId;
        this.userId = userId;
        this.gameDate = gameDate;
        this.round = round;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Game(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Integer tournamentId) {
        this.tournamentId = tournamentId;
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
        return "Game [id=" + id + ", tournamentId=" + tournamentId + ", tournamentTeamAId=" + tournamentTeamAId
                + ", tournamentTeamBId=" + tournamentTeamBId + ", userId=" + userId + ", gameDate=" + gameDate
                + ", round=" + round + ", tournamentTeamWinnerId=" + tournamentTeamWinnerId + ", latitude=" + latitude
                + ", longitude=" + longitude + ", status=" + status + "]";
    }
}
