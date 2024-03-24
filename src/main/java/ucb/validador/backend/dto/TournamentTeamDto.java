package ucb.validador.backend.dto;

public class TournamentTeamDto {
    private Integer id;
    private Integer tournamentId;
    private Integer teamId;
    private Boolean status;

    public TournamentTeamDto(Integer id, Integer tournamentId, Integer teamId, Boolean status) {
        this.id = id;
        this.tournamentId = tournamentId;
        this.teamId = teamId;
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
        return "TournamentTeamDto [id=" + id + ", tournamentId=" + tournamentId + ", teamId=" + teamId + ", status="
                + status + "]";
    }
}
