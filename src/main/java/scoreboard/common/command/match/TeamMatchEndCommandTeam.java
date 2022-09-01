package scoreboard.common.command.match;

import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.processor.TeamTournamentProcessor;

public class TeamMatchEndCommandTeam extends TeamMatchCommandTeam {

    public TeamMatchEndCommandTeam(TeamTournamentProcessor tournamentProcessor, TeamMatch match) {
        super(tournamentProcessor, match);
    }

    @Override
    public void execute() {
        tournamentProcessor.endMatch(match);
    }
}
