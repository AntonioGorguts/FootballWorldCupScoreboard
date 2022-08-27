package scoreboard.model.match;

import scoreboard.model.score.TeamScore;
import scoreboard.model.team.Team;

public interface TeamMatch extends Match {
    Team getHomeTeam();

    Team getAwayTeam();

    TeamScore getScore();

    Integer getScoreTotal();
}
