package scoreboard.common.factory;

import scoreboard.common.model.match.TeamMatch;

public interface TeamMatchFactory<Team> {
    TeamMatch createMatch(Team homeTeam, Team awayTeam);
}
