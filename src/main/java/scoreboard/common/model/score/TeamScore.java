package scoreboard.common.model.score;

public interface TeamScore extends Score{
    int getHomeScore();

    int getAwayScore();

    Integer getScoreTotal();
}
