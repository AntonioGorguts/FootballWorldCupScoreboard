package scoreboard.common.command.match;

import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.processor.TeamTournamentProcessor;

public class TeamMatchEndCommand extends TeamMatchCommand {

    public TeamMatchEndCommand(TeamTournamentProcessor tournamentProcessor, TeamMatch match) {
        super(tournamentProcessor, match);
    }

    @Override
    public void execute() {
        tournamentProcessor.endMatch(match);
    }

    @Override
    public String toString() {
        return TeamMatchEndCommand.class.getSimpleName() + " to match type" + match.getMatchType() + " " + match;
    }
}
