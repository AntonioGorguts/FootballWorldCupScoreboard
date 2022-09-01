package scoreboard.common.command.match;

import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.processor.TeamTournamentProcessor;

public class TeamMatchStartCommand extends TeamMatchCommand {

    public TeamMatchStartCommand(TeamTournamentProcessor tournamentProcessor, TeamMatch match) {
        super(tournamentProcessor, match);
    }

    @Override
    public void execute() {
        tournamentProcessor.startMatch(match);
    }


    @Override
    public String toString() {
        return TeamMatchStartCommand.class.getSimpleName() +
                " to match type " + match.getMatchType() +
                " match " + match;
    }
}
