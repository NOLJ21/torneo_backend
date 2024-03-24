package ucb.validador.backend.security.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "password_settings")
public class PasswordSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numberFail;
    private String pattern;
    private LocalDate expireDate;

    public PasswordSetting(Long id, Integer numberFail, String pattern, LocalDate expireDate) {
        this.id = id;
        this.numberFail = numberFail;
        this.pattern = pattern;
        this.expireDate = expireDate;
    }

    public PasswordSetting(Long id) {
        this.id = id;
    }

    public PasswordSetting() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberFail() {
        return numberFail;
    }

    public void setNumberFail(Integer numberFail) {
        this.numberFail = numberFail;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "PasswordSetting [id=" + id + ", numberFail=" + numberFail + ", pattern=" + pattern + ", expireDate="
                + expireDate + "]";
    }

}
