package scoreboard.common.model.score;

public interface TeamScore extends Score{
    Integer getHomeScore();

    Integer getAwayScore();

    Integer getScoreTotal();
}
