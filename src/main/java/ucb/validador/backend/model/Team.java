package ucb.validador.backend.model;

import javax.persistence.*;

import ucb.validador.backend.dto.DssTeamWinnerDto;

import java.time.LocalDate;

@Entity
@Table(name = "teams")
@NamedQueries({
        @NamedQuery(name = "Team.findAllByTournamentId", query = "SELECT te FROM Tournament to, TournamentTeam tote, Team te WHERE to.id = tote.tournamentId AND tote.teamId = te.id AND to.id = :tournamentId"),
        @NamedQuery(name = "Team.findByTournamentTeamId", query = "SELECT te FROM TournamentTeam tote, Team te WHERE tote.teamId = te.id AND tote.id = :tournamentTeamId"),
        @NamedQuery(name = "Team.findAllTodayByRefereeId", query = "SELECT te FROM Team te, TournamentTeam tote, Game ga WHERE te.id = tote.teamId AND tote.id = ga.tournamentTeamAId OR tote.id = ga.tournamentTeamBId AND ga.gameDate = CURRENT_DATE AND ga.userId = :refereeId GROUP BY te") })
@SqlResultSetMapping(name = "TeamWinnerCount", classes = @ConstructorResult(targetClass = DssTeamWinnerDto.class, columns = {
        @ColumnResult(name = "id", type = Integer.class),
        @ColumnResult(name = "name", type = String.class),
        @ColumnResult(name = "profile", type = String.class),
        @ColumnResult(name = "foundation", type = LocalDate.class),
        @ColumnResult(name = "user_id", type = Integer.class),
        @ColumnResult(name = "status", type = Boolean.class),
        @ColumnResult(name = "count", type = Integer.class) }))
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String profile;
    private LocalDate foundation;
    private Integer userId;
    private Boolean status = true;

    public Team() {
    }

    public Team(Integer id, String name, String profile, LocalDate foundation, Integer userId) {
        this.id = id;
        this.name = name;
        this.profile = profile;
        this.foundation = foundation;
        this.userId = userId;
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

    public LocalDate getFoundation() {
        return foundation;
    }

    public void setFoundation(LocalDate foundation) {
        this.foundation = foundation;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "Team [id=" + id + ", name=" + name + ", profile=" + profile + ", foundation=" + foundation + ", userId="
                + userId + ", status=" + status + "]";
    }

}
