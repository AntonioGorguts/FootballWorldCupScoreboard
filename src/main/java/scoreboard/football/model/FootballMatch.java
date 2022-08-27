package scoreboard.football.model;

import scoreboard.model.match.TeamMatch;
import scoreboard.model.score.TeamScore;
import scoreboard.model.team.Team;

import java.util.Date;

public class FootballMatch implements TeamMatch {
    @Override
    public Date getStartDate() {
        return null;
    }

    @Override
    public Team getHomeTeam() {
        return null;
    }

    @Override
    public Team getAwayTeam() {
        return null;
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
