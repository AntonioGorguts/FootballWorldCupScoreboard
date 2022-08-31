package scoreboard.common.model.match;

import scoreboard.common.model.score.TeamScore;
import scoreboard.common.model.team.Team;

public abstract class TeamMatch implements Match {
    public abstract Team getHomeTeam();

    public abstract Team getAwayTeam();

    public abstract TeamScore getScore();

    public abstract Integer getScoreTotal();

    public abstract String toStringWithScore();
}
