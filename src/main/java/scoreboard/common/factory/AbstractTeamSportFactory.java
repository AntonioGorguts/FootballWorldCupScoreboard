package scoreboard.common.factory;

import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.model.score.TeamScore;

public interface AbstractTeamSportFactory<Team> {
    Team createTeam(String name);
    TeamMatch createMatch(Team homeTeam, Team awayTeam);
    TeamScore createScore(int homeScore, int awayScore);
}
