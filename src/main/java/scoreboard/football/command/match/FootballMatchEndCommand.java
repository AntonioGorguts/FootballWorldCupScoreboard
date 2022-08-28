package scoreboard.football.command.match;

import scoreboard.football.model.FootballMatch;
import scoreboard.football.processor.FootballTournamentProcessor;

public class FootballMatchEndCommand extends FootballMatchCommand{
    public FootballMatchEndCommand(FootballTournamentProcessor footballTournamentProcessor, FootballMatch match) {
        super(footballTournamentProcessor, match);
    }

    @Override
    public void execute() {
        getFootballTournamentProcessor().endMatch(getMatch());
    }
}
