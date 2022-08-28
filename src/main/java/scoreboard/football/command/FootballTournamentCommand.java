package scoreboard.football.command;

import scoreboard.common.command.TournamentCommand;
import scoreboard.football.processor.FootballTournamentProcessor;

public abstract class FootballTournamentCommand implements TournamentCommand {

    private final FootballTournamentProcessor footballTournamentProcessor;

    public FootballTournamentCommand(FootballTournamentProcessor footballTournamentProcessor) {
        this.footballTournamentProcessor = footballTournamentProcessor;
    }

    public FootballTournamentProcessor getFootballTournamentProcessor() {
        return footballTournamentProcessor;
    }
}
