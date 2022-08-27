package scoreboard.football.model;

import scoreboard.common.model.score.TeamScore;

public class FootballScore implements TeamScore {
    private int homeScore = 0;
    private int awayScore = 0;
    @Override
    public int getHomeScore() {
        return 0;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    @Override
    public int getAwayScore() {
        return 0;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    @Override
    public Integer getScoreTotal() {
        return null;
    }
}
