package scoreboard.common.command.scoreboard;

import scoreboard.common.command.tournament.TeamTournamentCommand;
import scoreboard.common.processor.TeamTournamentProcessor;

import java.util.ArrayList;
import java.util.List;

public class TeamScoreboardExportCommand extends TeamTournamentCommand {

    private List<String> scoreboard = new ArrayList<>();

    public TeamScoreboardExportCommand(TeamTournamentProcessor tournamentProcessor) {
        super(tournamentProcessor);
    }

    @Override
    public String toString() {
        return TeamScoreboardExportCommand.class.getSimpleName();
    }

    public List<String> getScoreboard() {
        return scoreboard;
    }

    @Override
    public void execute() {
        scoreboard = tournamentProcessor.getScoreboard();
    }
}
