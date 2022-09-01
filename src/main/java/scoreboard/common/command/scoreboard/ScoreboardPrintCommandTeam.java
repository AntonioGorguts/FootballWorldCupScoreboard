package scoreboard.common.command.scoreboard;

import scoreboard.common.command.tournament.TeamTournamentCommand;
import scoreboard.common.processor.TeamTournamentProcessor;

public class ScoreboardPrintCommandTeam extends TeamTournamentCommand {

    public ScoreboardPrintCommandTeam(TeamTournamentProcessor tournamentProcessor) {
        super(tournamentProcessor);
    }

    @Override
    public void execute() {
        tournamentProcessor.printScoreboard();
    }
}
