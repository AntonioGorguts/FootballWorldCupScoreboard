package scoreboard.common.command.scoreboard;

import scoreboard.common.command.tournament.TournamentCommand;
import scoreboard.common.processor.TeamTournamentProcessor;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardExportCommand extends TournamentCommand {

    private List<String> scoreboard = new ArrayList<>();

    public ScoreboardExportCommand(TeamTournamentProcessor tournamentProcessor) {
        super(tournamentProcessor);
    }

    public List<String> getScoreboard() {
        return scoreboard;
    }

    @Override
    public void execute() {
        scoreboard = getTournamentProcessor().getScoreboard();
    }
}
