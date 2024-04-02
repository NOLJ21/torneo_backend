package ucb.validador.backend.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import ucb.validador.backend.security.model.LoginFail;

import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.transaction.Transactional;

public interface LoginFailRepository extends JpaRepository<LoginFail, Long> {
    LoginFail findByUserId(Long userId);

    @Procedure(name = "login_fail_true", procedureName = "login_fail_true")
    void login_fail_true(Long userId);

    @Procedure(name = "login_fail_false", procedureName = "login_fail_false")
    void login_fail_false(Long userId);

    @Modifying
    @Query(value = "UPDATE users " +
            "    SET status = FALSE " +
            "    WHERE id = :userId", nativeQuery = true)
    void loginFailFalsePart1(Long userId);

    @Modifying
    @Query(value = "UPDATE login_fails " +
            "    SET expire_date = CURRENT_TIMESTAMP + INTERVAL '1 minute' " +
            "    WHERE user_id = :userId", nativeQuery = true)
    void loginFailFalsePart2(Long userId);

    @Modifying
    @Query(value = "UPDATE users " +
            "    SET status = TRUE " +
            "    WHERE id = :userId", nativeQuery = true)
    void loginFailTruePart1(Long userId);

    @Modifying
    @Query(value = "UPDATE login_fails " +
            "    SET count = 0, " +
            "        expire_date = null " +
            "    WHERE user_id = :userId", nativeQuery = true)
    void loginFailTruePart2(Long userId);
}
