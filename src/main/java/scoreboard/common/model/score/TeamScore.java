package scoreboard.common.model.score;

public abstract class TeamScore implements Score{
    public abstract Integer getHomeScore();

    public abstract Integer getAwayScore();

    public abstract Integer getScoreTotal();
}
