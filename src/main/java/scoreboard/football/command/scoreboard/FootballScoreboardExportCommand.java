package scoreboard.football.command.scoreboard;

import scoreboard.football.command.FootballTournamentCommand;
import scoreboard.football.processor.FootballTournamentProcessor;

import java.util.ArrayList;
import java.util.List;

public class FootballScoreboardExportCommand extends FootballTournamentCommand {

    private List<String> scoreboard = new ArrayList<>();

    public FootballScoreboardExportCommand(FootballTournamentProcessor footballTournamentProcessor) {
        super(footballTournamentProcessor);
    }

    public List<String> getScoreboard() {
        return scoreboard;
    }

    @Override
    public void execute() {
        scoreboard = getFootballTournamentProcessor().getScoreboard();
    }
}
