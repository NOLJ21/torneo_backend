package ucb.validador.backend.model;

import javax.persistence.*;

import ucb.validador.backend.dto.DssPlayerGoalDto;
import ucb.validador.backend.dto.DssPlayerRedCardDto;
import ucb.validador.backend.dto.DssPlayerYellowCardDto;

import java.time.LocalDate;

@Entity
@Table(name = "players")
@NamedQueries({
        @NamedQuery(name = "Player.findAllByUserId", query = "SELECT pl FROM Team te, Player pl WHERE te.id = pl.teamId AND te.userId = :userId"),
        @NamedQuery(name = "Player.findAllByTournamentTeamId", query = "SELECT pl FROM TournamentTeam tote, Team te, Player pl WHERE tote.teamId = te.id AND te.id = pl.teamId AND tote.id = :tournamentTeamId") })

@SqlResultSetMapping(name = "PlayerGoalCount", classes = @ConstructorResult(targetClass = DssPlayerGoalDto.class, columns = {
        @ColumnResult(name = "id", type = Integer.class),
        @ColumnResult(name = "name", type = String.class),
        @ColumnResult(name = "profile", type = String.class),
        @ColumnResult(name = "ci", type = String.class),
        @ColumnResult(name = "birthdate", type = LocalDate.class),
        @ColumnResult(name = "position_id", type = Integer.class),
        @ColumnResult(name = "team_id", type = Integer.class),
        @ColumnResult(name = "status", type = Boolean.class),
        @ColumnResult(name = "goal", type = Integer.class) }))

@SqlResultSetMapping(name = "PlayerRedCardCount", classes = @ConstructorResult(targetClass = DssPlayerRedCardDto.class, columns = {
        @ColumnResult(name = "id", type = Integer.class),
        @ColumnResult(name = "name", type = String.class),
        @ColumnResult(name = "profile", type = String.class),
        @ColumnResult(name = "ci", type = String.class),
        @ColumnResult(name = "birthdate", type = LocalDate.class),
        @ColumnResult(name = "position_id", type = Integer.class),
        @ColumnResult(name = "team_id", type = Integer.class),
        @ColumnResult(name = "status", type = Boolean.class),
        @ColumnResult(name = "red", type = Integer.class) }))

@SqlResultSetMapping(name = "PlayerYellowCardCount", classes = @ConstructorResult(targetClass = DssPlayerYellowCardDto.class, columns = {
        @ColumnResult(name = "id", type = Integer.class),
        @ColumnResult(name = "name", type = String.class),
        @ColumnResult(name = "profile", type = String.class),
        @ColumnResult(name = "ci", type = String.class),
        @ColumnResult(name = "birthdate", type = LocalDate.class),
        @ColumnResult(name = "position_id", type = Integer.class),
        @ColumnResult(name = "team_id", type = Integer.class),
        @ColumnResult(name = "status", type = Boolean.class),
        @ColumnResult(name = "yellow", type = Integer.class) }))

public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String profile;
    private String ci;
    private LocalDate birthdate;
    private Integer positionId;
    private Integer teamId;
    private Boolean status = true;

    public Player() {
    }

    public Player(Integer id, String name, String profile, String ci, LocalDate birthdate, Integer positionId,
            Integer teamId) {
        this.id = id;
        this.name = name;
        this.profile = profile;
        this.ci = ci;
        this.birthdate = birthdate;
        this.positionId = positionId;
        this.teamId = teamId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", name=" + name + ", profile=" + profile + ", ci=" + ci + ", birthdate="
                + birthdate + ", positionId=" + positionId + ", teamId=" + teamId + ", status=" + status + "]";
    }

}
