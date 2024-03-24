package ucb.validador.backend.dto;

import java.time.LocalDate;

public class DssPlayerGoalDto {
    private Integer id;
    private String name;
    private String profile;
    private String ci;
    private LocalDate birthdate;
    private Integer positionId;
    private Integer teamId;
    private Boolean status;

    // Fields for DSS
    private Integer goal;

    public DssPlayerGoalDto(Integer id, String name, String profile, String ci, LocalDate birthdate, Integer positionId,
            Integer teamId, Boolean status, Integer goal) {
        this.id = id;
        this.name = name;
        this.profile = profile;
        this.ci = ci;
        this.birthdate = birthdate;
        this.positionId = positionId;
        this.teamId = teamId;
        this.status = status;
        this.goal = goal;
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
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

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    @Override
    public String toString() {
        return "DssPlayerGoalDto [id=" + id + ", name=" + name + ", profile=" + profile + ", ci=" + ci + ", birthdate="
                + birthdate + ", positionId=" + positionId + ", teamId=" + teamId + ", status=" + status + ", goal="
                + goal + "]";
    }
}
