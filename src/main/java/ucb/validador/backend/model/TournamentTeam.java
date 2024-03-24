package ucb.validador.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tournaments_teams")
@NamedQueries({
        @NamedQuery(name = "TournamentTeam.deleteByTournamentIdAndTeamId", query = "DELETE FROM TournamentTeam t WHERE t.tournamentId = :tournamentId AND t.teamId = :teamId"),
        @NamedQuery(name = "TournamentTeam.findAllTodayByRefereeId", query = "SELECT tote FROM TournamentTeam tote, Game ga WHERE tote.id = ga.tournamentTeamAId OR tote.id = ga.tournamentTeamBId AND ga.gameDate = CURRENT_DATE AND ga.userId = :refereeId GROUP BY tote") })
public class TournamentTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer tournamentId;
    private Integer teamId;
    private Boolean status = true;

    public TournamentTeam() {
    }

    public TournamentTeam(Integer id, Integer tournamentId, Integer teamId) {
        this.id = id;
        this.tournamentId = tournamentId;
        this.teamId = teamId;
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

    @Override
    public String toString() {
        return "TournamentTeam [id=" + id + ", tournamentId=" + tournamentId + ", teamId=" + teamId + ", status="
                + status + "]";
    }

}
