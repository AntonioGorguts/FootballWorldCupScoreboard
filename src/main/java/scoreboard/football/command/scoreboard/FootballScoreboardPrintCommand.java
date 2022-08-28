package scoreboard.football.command.scoreboard;

import scoreboard.football.command.FootballTournamentCommand;
import scoreboard.football.processor.FootballTournamentProcessor;

public class FootballScoreboardPrintCommand extends FootballTournamentCommand {
    public FootballScoreboardPrintCommand(FootballTournamentProcessor footballTournamentProcessor) {
        super(footballTournamentProcessor);
    }

    @Override
    public void execute() {
        getFootballTournamentProcessor().printScoreboard();
    }
}
