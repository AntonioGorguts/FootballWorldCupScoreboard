package scoreboard.football.processor;

import scoreboard.football.model.FootballMatch;
import scoreboard.football.model.FootballScore;
import scoreboard.common.processor.TeamProcessor;
import scoreboard.common.processor.TournamentProcessor;

public class FootballTournamentProcessor implements TournamentProcessor<FootballMatch>, TeamProcessor<FootballMatch, FootballScore> {
    @Override
    public void updateScore(FootballMatch footballMatch, FootballScore footballScore) {

    }

    @Override
    public void startMatch(FootballMatch footballMatch) {

    }

    @Override
    public void endMatch(FootballMatch footballMatch) {

    }
}
