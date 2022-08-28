package scoreboard.football.processor;

import org.apache.log4j.Logger;
import scoreboard.common.model.scoreboard.Scoreboard;
import scoreboard.football.model.FootballMatch;
import scoreboard.football.model.FootballScore;
import scoreboard.common.processor.TeamProcessor;
import scoreboard.common.processor.TournamentProcessor;
import scoreboard.football.model.FootballTournament;

import java.util.List;

public class FootballTournamentProcessor implements TournamentProcessor<FootballMatch>, TeamProcessor<FootballMatch, FootballScore>, Scoreboard {

    private static final Logger LOGGER = Logger.getLogger(FootballTournamentProcessor.class);

    private final FootballTournament footballTournament;

    public FootballTournamentProcessor(FootballTournament footballTournament) {
        this.footballTournament = footballTournament;
    }

    public FootballTournament getFootballTournament() {
        return footballTournament;
    }
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
