package scoreboard.football.model;

import scoreboard.common.model.score.TeamScore;
import scoreboard.util.ErrorMessageUtil;

public class FootballScore implements TeamScore {
    private int homeScore = 0;
    private int awayScore = 0;

    @Override
    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        if (homeScore < 0){
            throw new IllegalArgumentException(ErrorMessageUtil.INVALID_HOME_SCORE);
        }
        this.homeScore = homeScore;
    }

    @Override
    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        if (awayScore < 0){
            throw new IllegalArgumentException(ErrorMessageUtil.INVALID_AWAY_SCORE);
        }
        this.awayScore = awayScore;
    }

    @Override
    public Integer getScoreTotal() {
        return homeScore + awayScore;
    }
}
