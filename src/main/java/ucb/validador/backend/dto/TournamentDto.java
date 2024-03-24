package ucb.validador.backend.dto;

import java.time.LocalDate;

public class TournamentDto {
    private Integer id;
    private String name;
    private Integer number;
    private Integer round;
    private LocalDate start;
    private LocalDate finish;
    private Boolean status;

    public TournamentDto(Integer id, String name, Integer number, Integer round, LocalDate start, LocalDate finish,
            Boolean status) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.round = round;
        this.start = start;
        this.finish = finish;
        this.status = status;
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

    @Override
    public String toString() {
        return "TournamentDto [id=" + id + ", name=" + name + ", number=" + number + ", round=" + round + ", start="
                + start + ", finish=" + finish + ", status=" + status + "]";
    }
}
