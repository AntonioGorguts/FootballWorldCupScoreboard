package scoreboard.football.command.match;

import scoreboard.football.model.FootballMatch;
import scoreboard.football.processor.FootballTournamentProcessor;

public class FootballMatchStartCommand extends FootballMatchCommand{

    public FootballMatchStartCommand(FootballTournamentProcessor footballTournamentProcessor, FootballMatch match) {
        super(footballTournamentProcessor, match);
    }

    @Override
    public void execute() {
        getFootballTournamentProcessor().startMatch(getMatch());
    }
}
