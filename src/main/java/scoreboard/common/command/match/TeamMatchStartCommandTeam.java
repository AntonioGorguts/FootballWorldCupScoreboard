package scoreboard.common.command.match;

import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.processor.TeamTournamentProcessor;

public class TeamMatchStartCommandTeam extends TeamMatchCommandTeam {

    public TeamMatchStartCommandTeam(TeamTournamentProcessor tournamentProcessor, TeamMatch match) {
        super(tournamentProcessor, match);
    }

    @Override
    public void execute() {
        tournamentProcessor.startMatch(match);
    }
}
