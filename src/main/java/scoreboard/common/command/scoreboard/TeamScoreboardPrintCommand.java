package scoreboard.common.command.scoreboard;

import scoreboard.common.command.tournament.TeamTournamentCommand;
import scoreboard.common.processor.TeamTournamentProcessor;

public class TeamScoreboardPrintCommand extends TeamTournamentCommand {

    public TeamScoreboardPrintCommand(TeamTournamentProcessor tournamentProcessor) {
        super(tournamentProcessor);
    }

    @Override
    public String toString() {
        return TeamScoreboardPrintCommand.class.getSimpleName();
    }

    @Override
    public void execute() {
        tournamentProcessor.printScoreboard();
    }
}
