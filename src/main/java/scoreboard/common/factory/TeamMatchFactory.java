package scoreboard.common.factory;

import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.model.team.Team;

public interface TeamMatchFactory<T extends Team> {
    TeamMatch createMatch(T homeTeam, T awayTeam);
}
