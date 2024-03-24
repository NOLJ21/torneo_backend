package ucb.validador.backend.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ucb.validador.backend.dto.DssPlayerGoalDto;
import ucb.validador.backend.dto.DssPlayerRedCardDto;
import ucb.validador.backend.dto.DssPlayerYellowCardDto;
import ucb.validador.backend.dto.DssTeamWinnerDto;

@Service
public class DssService {
    EntityManager entityManager;

    @Autowired
    public DssService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public List<DssTeamWinnerDto> findTeamWinnerCountDto() {
        @SuppressWarnings("unchecked")
        List<DssTeamWinnerDto> dssTeamWinnerDtos = (List<DssTeamWinnerDto>) entityManager.createNativeQuery(
                "SELECT te.*, COUNT(te.id) AS count from teams AS te, tournaments_teams AS to_te, games AS ga WHERE te.id = to_te.team_id AND to_te.id = ga.tournament_team_winner_id GROUP BY te.id;",
                "TeamWinnerCount").getResultList();
        return dssTeamWinnerDtos;
    }

    @Transactional
    public List<DssTeamWinnerDto> findTeamWinnerCountByUserIdDto(Integer userId) {
        @SuppressWarnings("unchecked")
        List<DssTeamWinnerDto> dssTeamWinnerDtos = (List<DssTeamWinnerDto>) entityManager.createNativeQuery(
                "SELECT te.*, COUNT(te.id) AS count from teams AS te, tournaments_teams AS to_te, games AS ga WHERE te.id = to_te.team_id AND to_te.id = ga.tournament_team_winner_id AND te.user_id = :userId GROUP BY te.id;",
                "TeamWinnerCount").setParameter("userId", userId).getResultList();
        return dssTeamWinnerDtos;
    }

    @Transactional
    public List<DssPlayerGoalDto> findPlayerGoalCountDto() {
        @SuppressWarnings("unchecked")
        List<DssPlayerGoalDto> dssPlayerGoalDtos = (List<DssPlayerGoalDto>) entityManager.createNativeQuery(
                "SELECT pl.*, SUM(go.goal) AS goal from teams AS te, players AS pl, goals as go WHERE te.id = pl.team_id AND  pl.id = go.player_id GROUP BY (pl.id);",
                "PlayerGoalCount").getResultList();
        return dssPlayerGoalDtos;
    }

    @Transactional
    public List<DssPlayerGoalDto> findPlayerGoalCountByUserIdDto(Integer userId) {
        @SuppressWarnings("unchecked")
        List<DssPlayerGoalDto> dssPlayerGoalDtos = (List<DssPlayerGoalDto>) entityManager.createNativeQuery(
                "SELECT pl.*, SUM(go.goal) AS goal from teams AS te, players AS pl, goals as go WHERE te.id = pl.team_id AND  pl.id = go.player_id AND te.user_id = :userId GROUP BY (pl.id);",
                "PlayerGoalCount").setParameter("userId", userId).getResultList();
        return dssPlayerGoalDtos;
    }

    @Transactional
    public List<DssPlayerRedCardDto> findPlayerRedCardCountDto() {
        @SuppressWarnings("unchecked")
        List<DssPlayerRedCardDto> dssPlayerRedCardDtos = (List<DssPlayerRedCardDto>) entityManager.createNativeQuery(
                "SELECT pl.*, SUM(rc.red_card) AS red from teams AS te, players AS pl, red_cards as rc WHERE te.id = pl.team_id AND  pl.id = rc.player_id GROUP BY (pl.id);",
                "PlayerRedCardCount").getResultList();
        return dssPlayerRedCardDtos;
    }

    @Transactional
    public List<DssPlayerRedCardDto> findPlayerRedCardCountByUserIdDto(Integer userId) {
        @SuppressWarnings("unchecked")
        List<DssPlayerRedCardDto> dssPlayerRedCardDtos = (List<DssPlayerRedCardDto>) entityManager.createNativeQuery(
                "SELECT pl.*, SUM(rc.red_card) AS red from teams AS te, players AS pl, red_cards as rc WHERE te.id = pl.team_id AND  pl.id = rc.player_id AND te.user_id = :userId GROUP BY (pl.id);",
                "PlayerRedCardCount").setParameter("userId", userId).getResultList();
        return dssPlayerRedCardDtos;
    }

    @Transactional
    public List<DssPlayerYellowCardDto> findPlayerYellowCardCountDto() {
        @SuppressWarnings("unchecked")
        List<DssPlayerYellowCardDto> dssPlayerYellowCardDtos = (List<DssPlayerYellowCardDto>) entityManager
                .createNativeQuery(
                        "SELECT pl.*, SUM(yc.yellow_card) AS yellow from teams AS te, players AS pl, yellow_cards as yc WHERE te.id = pl.team_id AND  pl.id = yc.player_id GROUP BY (pl.id);",
                        "PlayerYellowCardCount")
                .getResultList();
        return dssPlayerYellowCardDtos;
    }

    @Transactional
    public List<DssPlayerYellowCardDto> findPlayerYellowCardCountByUserIdDto(Integer userId) {
        @SuppressWarnings("unchecked")
        List<DssPlayerYellowCardDto> dssPlayerYellowCardDtos = (List<DssPlayerYellowCardDto>) entityManager
                .createNativeQuery(
                        "SELECT pl.*, SUM(yc.yellow_card) AS yellow from teams AS te, players AS pl, yellow_cards as yc WHERE te.id = pl.team_id AND  pl.id = yc.player_id AND te.user_id = :userId GROUP BY (pl.id);",
                        "PlayerYellowCardCount")
                .setParameter("userId", userId).getResultList();
        return dssPlayerYellowCardDtos;
    }
}
