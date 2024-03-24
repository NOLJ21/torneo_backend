CREATE DATABASE IF NOT EXISTS validatorDatabase;
CREATE TABLE IF NOT EXISTS roles (
    id INT(11) NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS users (
    id INT(20) NOT NULL AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(200) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS user_roles (
    user_id INT(20) NOT NULL,
    role_id INT(11) NOT NULL,
    PRIMARY KEY(user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);
CREATE TABLE IF NOT EXISTS refreshtoken (
    id INT(20) NOT NULL AUTO_INCREMENT,
    user_id INT(20) NOT NULL,
    token VARCHAR(255) NOT NULL,
    expiry_date DATETIME NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
CREATE TABLE IF NOT EXISTS teams (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    profile VARCHAR(255) NOT NULL,
    foundation DATE NOT NULL,
    user_id INT NOT NULL,
    status BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
CREATE TABLE IF NOT EXISTS positions (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS players (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    profile VARCHAR(255) NOT NULL,
    ci VARCHAR(20) NOT NULL,
    birthdate DATE NOT NULL,
    position_id INT NOT NULL,
    team_id INT NOT NULL,
    status BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id),
    FOREIGN KEY (position_id) REFERENCES positions(id),
    FOREIGN KEY (team_id) REFERENCES teams(id)
);
CREATE TABLE IF NOT EXISTS tournaments (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    number INT NOT NULL,
    round INT NOT NULL,
    start DATE NOT NULL,
    finish DATE NOT NULL,
    status BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS tournaments_teams (
    id INT NOT NULL AUTO_INCREMENT,
    tournament_id INT NOT NULL,
    team_id INT NOT NULL,
    status BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id),
    UNIQUE KEY (tournament_id, team_id),
    FOREIGN KEY (tournament_id) REFERENCES tournaments(id),
    FOREIGN KEY (team_id) REFERENCES teams(id)
);
CREATE TABLE IF NOT EXISTS games (
    id INT NOT NULL AUTO_INCREMENT,
    tournament_id INT NOT NULL,
    tournament_team_a_id INT NOT NULL,
    tournament_team_b_id INT NOT NULL,
    user_id INT NOT NULL,
    game_date DATE NOT NULL,
    round INT NOT NULL,
    tournament_team_winner_id INT,
    latitude decimal(10, 8) NOT NULL,
    longitude decimal(11, 8) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id),
    FOREIGN KEY (tournament_id) REFERENCES tournaments(id),
    FOREIGN KEY (tournament_team_a_id) REFERENCES tournaments_teams(id),
    FOREIGN KEY (tournament_team_b_id) REFERENCES tournaments_teams(id),
    FOREIGN KEY (tournament_team_winner_id) REFERENCES tournaments_teams(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
CREATE TABLE IF NOT EXISTS yellow_cards (
    id INT NOT NULL AUTO_INCREMENT,
    game_id INT NOT NULL,
    player_id INT NOT NULL,
    yellow_card INT NOT NULL,
    time TIME NOT NULL,
    status BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES games(id),
    FOREIGN KEY (player_id) REFERENCES players(id)
);
CREATE TABLE IF NOT EXISTS red_cards (
    id INT NOT NULL AUTO_INCREMENT,
    game_id INT NOT NULL,
    player_id INT NOT NULL,
    red_card INT NOT NULL,
    time TIME NOT NULL,
    status BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES games(id),
    FOREIGN KEY (player_id) REFERENCES players(id)
);
CREATE TABLE IF NOT EXISTS goals (
    id INT NOT NULL AUTO_INCREMENT,
    game_id INT NOT NULL,
    player_id INT NOT NULL,
    goal INT NOT NULL DEFAULT 1,
    time TIME NOT NULL,
    status BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES games(id),
    FOREIGN KEY (player_id) REFERENCES players(id)
);
CREATE TABLE IF NOT EXISTS login_fails (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL UNIQUE,
    count INT NOT NULL DEFAULT 0,
    expire_date DATETIME,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS password_settings (
    id INT NOT NULL AUTO_INCREMENT,
    number_fail INT NOT NULL,
    pattern VARCHAR(200) NOT NULL,
    expire_date DATETIME,
    PRIMARY KEY(id)
);