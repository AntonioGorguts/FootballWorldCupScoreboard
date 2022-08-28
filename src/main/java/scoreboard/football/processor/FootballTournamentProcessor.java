package scoreboard.football.processor;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import scoreboard.common.model.scoreboard.Scoreboard;
import scoreboard.exception.MatchCommonException;
import scoreboard.football.model.FootballMatch;
import scoreboard.football.model.FootballScore;
import scoreboard.common.processor.TeamProcessor;
import scoreboard.common.processor.TournamentProcessor;
import scoreboard.football.model.FootballTournament;
import scoreboard.util.ErrorMessageUtil;

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
        matchCommonValidation(footballMatch);
        if(footballScore == null){
            throw new MatchCommonException(ErrorMessageUtil.SCORE_NOT_NULL);
        }
    }

    @Override
    public void startMatch(FootballMatch footballMatch) {
        matchCommonValidation(footballMatch);
    }

    @Override
    public void endMatch(FootballMatch footballMatch) {
        matchCommonValidation(footballMatch);
    }

    @Override
    public void printScoreboard() {

    }

    @Override
    public List<String> getScoreboard() {
        return null;
    }

    private void matchCommonValidation(FootballMatch match){
        if (match == null){
            throw new MatchCommonException(ErrorMessageUtil.MATCH_NOT_NULL);
        }
        if (match.getHomeTeam() == null || StringUtils.isBlank(match.getHomeTeam().getName())){
            throw new MatchCommonException(ErrorMessageUtil.HOME_TEAM_NOT_BLANK);
        }
        if (match.getAwayTeam() == null || StringUtils.isBlank(match.getAwayTeam().getName())){
            throw new MatchCommonException(ErrorMessageUtil.AWAY_TEAM_NOT_BLANK);
        }
    }
}
