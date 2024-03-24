package ucb.validador.backend.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import ucb.validador.backend.security.model.LoginFail;

public interface LoginFailRepository extends JpaRepository<LoginFail, Long> {
    LoginFail findByUserId(Long userId);

    @Procedure("login_fail_true")
    void login_fail_true(Long userId);

    @Procedure("login_fail_false")
    void login_fail_false(Long userId);
}
