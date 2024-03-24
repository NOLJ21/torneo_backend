-- Roles
INSERT INTO roles(name)
VALUES('ROLE_ORGANIZER');
INSERT INTO roles(name)
VALUES('ROLE_REFEREE');
INSERT INTO roles(name)
VALUES('ROLE_COACH');
-- Password setting
INSERT INTO password_settings (number_fail, pattern, expire_date)
VALUES (
        3,
        '^(?=(.*[A-Z]){1,})(?=(.*[a-z]){1,})(?=(.*[0-9]){1,})(?=(.*[!]){1,}).{8,}$',
        '2023-01-01'
    );
-- Tournaments
INSERT INTO tournaments (name, number, round, start, finish, status)
VALUES (
        "Cahmpionship 1",
        16,
        4,
        "2023-01-01",
        "2023-01-01",
        true
    );
INSERT INTO tournaments (name, number, round, start, finish, status)
VALUES (
        "Cahmpionship 2",
        16,
        4,
        "2023-01-01",
        "2023-01-01",
        true
    );
INSERT INTO tournaments (name, number, round, start, finish, status)
VALUES (
        "Cahmpionship 3",
        16,
        4,
        "2023-01-01",
        "2023-01-01",
        true
    );
INSERT INTO tournaments (name, number, round, start, finish, status)
VALUES (
        "Cahmpionship 4",
        16,
        4,
        "2023-01-01",
        "2023-01-01",
        true
    );
INSERT INTO tournaments (name, number, round, start, finish, status)
VALUES (
        "Cahmpionship 5",
        16,
        4,
        "2023-01-01",
        "2023-01-01",
        true
    );
-- Position
INSERT INTO positions (name)
VALUES ('Goal Keeper');
INSERT INTO positions (name)
VALUES ('Defense');
INSERT INTO positions (name)
VALUES ('Midfield');
INSERT INTO positions (name)
VALUES ('Striker');
-- Stored precedures
DELIMITER $$ CREATE PROCEDURE login_fail_true(IN userId INT) BEGIN
UPDATE users
SET status = 1
WHERE id = userId;
UPDATE login_fails
SET count = 0,
    expire_date = null
WHERE user_id = userId;
END $$ DELIMITER;
DELIMITER $$ CREATE PROCEDURE login_fail_false(IN userId INT) BEGIN
UPDATE users
SET status = 0
WHERE id = userId;
UPDATE login_fails
SET expire_date = CURRENT_TIMESTAMP + INTERVAL 1 MINUTE
WHERE user_id = userId;
END $$ DELIMITER;