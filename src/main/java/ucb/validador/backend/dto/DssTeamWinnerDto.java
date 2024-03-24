package ucb.validador.backend.dto;

import java.time.LocalDate;

public class DssTeamWinnerDto {
    private Integer id;
    private String name;
    private String profile;
    private LocalDate foundation;
    private Integer userId;
    private Boolean status;

    // Fields for DSS
    private Integer count;

    public DssTeamWinnerDto(Integer id, String name, String profile, LocalDate foundation, Integer userId,
            Boolean status, Integer count) {
        this.id = id;
        this.name = name;
        this.profile = profile;
        this.foundation = foundation;
        this.userId = userId;
        this.status = status;
        this.count = count;
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

    public LocalDate getFoundation() {
        return foundation;
    }

    public void setFoundation(LocalDate foundation) {
        this.foundation = foundation;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "DssTeamWinnerDto [id=" + id + ", name=" + name + ", profile=" + profile + ", foundation=" + foundation
                + ", userId=" + userId + ", status=" + status + ", count=" + count + "]";
    }
}
