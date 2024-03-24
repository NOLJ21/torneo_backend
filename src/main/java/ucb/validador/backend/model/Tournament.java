package ucb.validador.backend.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tournaments")
@NamedQueries({
        @NamedQuery(name = "Tournament.findAllByUserId", query = "SELECT to FROM Tournament to, TournamentTeam tote, Team te WHERE to.id = tote.tournamentId AND tote.teamId = te.id AND te.userId = :userId GROUP BY to"),
        @NamedQuery(name = "Tournament.findAllByRefereeId", query = "SELECT to FROM Tournament to, Game ga WHERE to.id = ga.tournamentId AND ga.userId = :refereeId GROUP BY to") })
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer number;
    private Integer round;
    private LocalDate start;
    private LocalDate finish;
    private Boolean status = true;

    public Tournament() {
    }

    public Tournament(Integer id, String name, Integer number, LocalDate start, LocalDate finish) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.round = (int) Math.sqrt(number.doubleValue());
        this.start = start;
        this.finish = finish;
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

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getFinish() {
        return finish;
    }

    public void setFinish(LocalDate finish) {
        this.finish = finish;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    @Override
    public String toString() {
        return "Tournament [id=" + id + ", name=" + name + ", number=" + number + ", round=" + round + ", start="
                + start + ", finish=" + finish + ", status=" + status + "]";
    }

}