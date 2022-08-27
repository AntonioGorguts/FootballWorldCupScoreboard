package scoreboard.football.model;

import scoreboard.common.model.score.TeamScore;

public class FootballScore implements TeamScore {
    @Override
    public int getHomeScore() {
        return 0;
    }

    @Override
    public int getAwayScore() {
        return 0;
    }

    @Override
    public Integer getScoreTotal() {
        return null;
    }
}
