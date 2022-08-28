package scoreboard.football.processor;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import scoreboard.common.model.scoreboard.Scoreboard;
import scoreboard.exception.MatchCommonException;
import scoreboard.football.model.FootballMatch;
import scoreboard.football.model.FootballScore;
import scoreboard.common.processor.TeamProcessor;
import scoreboard.common.processor.TournamentProcessor;
import scoreboard.football.model.FootballTeam;
import scoreboard.football.model.FootballTournament;
import scoreboard.util.ErrorMessageUtil;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
    public void updateScore(FootballMatch match, FootballScore score) {
        matchCommonValidation(match);
        if(score == null){
            throw new MatchCommonException(ErrorMessageUtil.SCORE_NOT_NULL);
        }
    }

    @Override
    public void startMatch(FootballMatch match) {
        matchCommonValidation(match);
        List<FootballMatch> footballMatches = footballTournament.getActiveMatches();
        Set<FootballTeam> activeTeams = footballTournament.getActiveTeams();
        FootballTeam homeTeam = match.getHomeTeam();
        FootballTeam awayTeam = match.getAwayTeam();

        if(footballMatches.contains(match)) {
            LOGGER.error(String.format("Can't start the new match %s. It's already in progress!!", match));
            throw new MatchCommonException(ErrorMessageUtil.MATCH_ALREADY_PLAYING);
        }
        if (activeTeams.contains(homeTeam)){
            LOGGER.error(String.format("Can't start the new match %s. Home team: %s is already participating in the match!",
                    match, homeTeam));
            throw new MatchCommonException(ErrorMessageUtil.HOME_TEAM_ALREADY_PLAYING);
        }
        if (activeTeams.contains(awayTeam)){
            LOGGER.error(String.format("Can't start the new match %s. Away team: %s is already participating in the match!",
                    match, awayTeam));
            throw new MatchCommonException(ErrorMessageUtil.AWAY_TEAM_ALREADY_PLAYING);
        }

        Date now = new Date();
        if(match.getStartDate() == null) {
            match.setStartDate(now);
        } else if (match.getStartDate().after(now)) {
            LOGGER.error(String.format("Can't start the new match %s. The match start date is after the current date!", match));
            throw new MatchCommonException(ErrorMessageUtil.MATCH_TIME_IS_IN_FUTURE);
        }

        activeTeams.add(homeTeam);
        activeTeams.add(awayTeam);
        footballTournament.getActiveMatches().add(match);
        LOGGER.info(String.format("Football Match %s started at %s", match, now));
    }

    @Override
    public void endMatch(FootballMatch match) {
        matchCommonValidation(match);
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
