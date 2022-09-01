package scoreboard.common.command.scoreboard;

import scoreboard.common.command.tournament.TeamTournamentCommand;
import scoreboard.common.processor.TeamTournamentProcessor;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardExportCommandTeam extends TeamTournamentCommand {

    private List<String> scoreboard = new ArrayList<>();

    public ScoreboardExportCommandTeam(TeamTournamentProcessor tournamentProcessor) {
        super(tournamentProcessor);
    }

    public List<String> getScoreboard() {
        return scoreboard;
    }

    @Override
    public void execute() {
        scoreboard = tournamentProcessor.getScoreboard();
    }
}
