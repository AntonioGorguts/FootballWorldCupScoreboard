package scoreboard.football.processor;

import scoreboard.common.model.scoreboard.Scoreboard;
import scoreboard.football.model.FootballMatch;
import scoreboard.football.model.FootballScore;
import scoreboard.common.processor.TeamProcessor;
import scoreboard.common.processor.TournamentProcessor;

import java.util.List;

public class FootballTournamentProcessor implements TournamentProcessor<FootballMatch>, TeamProcessor<FootballMatch, FootballScore>, Scoreboard {
    @Override
    public void updateScore(FootballMatch footballMatch, FootballScore footballScore) {

    }

    @Override
    public void startMatch(FootballMatch footballMatch) {

    }

    @Override
    public void endMatch(FootballMatch footballMatch) {

    }

    @Override
    public void printScoreboard() {

    }

    @Override
    public List<String> getScoreboard() {
        return null;
    }
}
