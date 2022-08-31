package scoreboard.common.command.scoreboard;

import scoreboard.common.command.tournament.TournamentCommand;
import scoreboard.common.processor.TeamTournamentProcessor;

public class ScoreboardPrintCommand extends TournamentCommand {

    public ScoreboardPrintCommand(TeamTournamentProcessor tournamentProcessor) {
        super(tournamentProcessor);
    }

    @Override
    public void execute() {
        getTournamentProcessor().printScoreboard();
    }
}
