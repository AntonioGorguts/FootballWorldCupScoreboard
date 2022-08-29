package scoreboard.football.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import scoreboard.common.model.score.TeamScore;
import scoreboard.util.ErrorMessageUtil;

public class FootballScore implements TeamScore {
    private Integer homeScore;
    private Integer awayScore;

    public FootballScore(int homeScore, int awayScore) {
        if (homeScore < 0){
            throw new IllegalArgumentException(ErrorMessageUtil.INVALID_HOME_SCORE);
        }
        if (awayScore < 0){
            throw new IllegalArgumentException(ErrorMessageUtil.INVALID_AWAY_SCORE);
        }
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    @Override
    public Integer getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        if (homeScore < 0){
            throw new IllegalArgumentException(ErrorMessageUtil.INVALID_HOME_SCORE);
        }
        if (this.homeScore > homeScore){
            throw new IllegalArgumentException(ErrorMessageUtil.HOME_SCORE_IS_LESSER_THAN_BEFORE);
        }
        this.homeScore = homeScore;
    }

    @Override
    public Integer getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        if (awayScore < 0){
            throw new IllegalArgumentException(ErrorMessageUtil.INVALID_AWAY_SCORE);
        }
        if (this.awayScore > awayScore){
            throw new IllegalArgumentException(ErrorMessageUtil.AWAY_SCORE_IS_LESSER_THAN_BEFORE);
        }
        this.awayScore = awayScore;
    }

    @Override
    public Integer getScoreTotal() {
        return homeScore + awayScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof FootballScore)) return false;

        FootballScore that = (FootballScore) o;

        return new EqualsBuilder().append(homeScore, that.homeScore).append(awayScore, that.awayScore).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(homeScore).append(awayScore).toHashCode();
    }
}
