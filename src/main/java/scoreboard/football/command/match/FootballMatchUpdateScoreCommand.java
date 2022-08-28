package scoreboard.football.command.match;

import scoreboard.football.model.FootballMatch;
import scoreboard.football.model.FootballScore;
import scoreboard.football.processor.FootballTournamentProcessor;

public class FootballMatchUpdateScoreCommand extends FootballMatchCommand{
    private final FootballScore footballScore;

    public FootballMatchUpdateScoreCommand(FootballTournamentProcessor footballTournamentProcessor, FootballMatch match, FootballScore footballScore) {
        super(footballTournamentProcessor, match);
        this.footballScore = footballScore;
    }

    @Override
    public void execute() {
        getFootballTournamentProcessor().updateScore(getMatch(), footballScore);
    }
}
