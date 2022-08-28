package scoreboard.football.command.match;

import scoreboard.football.command.FootballTournamentCommand;
import scoreboard.football.model.FootballMatch;
import scoreboard.football.processor.FootballTournamentProcessor;

public abstract class FootballMatchCommand extends FootballTournamentCommand {
    private final FootballMatch match;

    public FootballMatchCommand(FootballTournamentProcessor footballTournamentProcessor, FootballMatch match) {
        super(footballTournamentProcessor);
        this.match = match;
    }

    public FootballMatch getMatch() {
        return match;
    }
}
