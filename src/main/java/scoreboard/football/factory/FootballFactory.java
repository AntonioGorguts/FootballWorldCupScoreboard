package scoreboard.football.factory;

import scoreboard.common.factory.TeamFactory;
import scoreboard.common.factory.TeamMatchFactory;
import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.model.team.Team;
import scoreboard.football.model.FootballTeam;

public class FootballFactory implements TeamFactory, TeamMatchFactory<FootballTeam> {
    @Override
    public FootballTeam createTeam(String name) {
        return null;
    }

    @Override
    public TeamMatch createMatch(FootballTeam homeTeam, FootballTeam awayTeam) {
        return null;
    }
}
