package scoreboard.common.model.match;

import scoreboard.common.model.score.TeamScore;
import scoreboard.common.model.team.Team;

public interface TeamMatch extends Match {
    Team getHomeTeam();

    Team getAwayTeam();

    TeamScore getScore();

    Integer getScoreTotal();
}
