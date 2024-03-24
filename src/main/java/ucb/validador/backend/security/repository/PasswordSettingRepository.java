package ucb.validador.backend.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ucb.validador.backend.security.model.PasswordSetting;

public interface PasswordSettingRepository extends JpaRepository<PasswordSetting, Long> {

}
