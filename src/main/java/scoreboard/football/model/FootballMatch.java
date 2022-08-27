package scoreboard.football.model;

import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.model.score.TeamScore;
import scoreboard.common.model.team.Team;

import java.util.Date;

public class FootballMatch implements TeamMatch {

    private final FootballTeam homeTeam;
    private final FootballTeam awayTeam;

    public FootballMatch(FootballTeam homeTeam, FootballTeam awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    @Override
    public Date getStartDate() {
        return null;
    }

    @Override
    public Team getHomeTeam() {
        return homeTeam;
    }

    @Override
    public Team getAwayTeam() {
        return awayTeam;
    }

    @Override
    public TeamScore getScore() {
        return null;
    }

    @Override
    public Integer getScoreTotal() {
        return null;
    }
}
