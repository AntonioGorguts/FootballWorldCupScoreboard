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
import scoreboard.util.MatchMessageUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

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
        List<FootballMatch> footballMatches = footballTournament.getActiveMatches();
        if(footballMatches.contains(match)){
            FootballMatch footballMatch = footballMatches.get(footballMatches.indexOf(match));
            if (footballMatch.getScore().equals(score)){
                LOGGER.info(String.format("Score for Football Match %s is already %s", match, score));
            } else {
                footballMatch.getScore().setHomeScore(score.getHomeScore());
                footballMatch.getScore().setAwayScore(score.getAwayScore());
                LOGGER.info(String.format("Score for Football Match %s updated at %s to %s",
                        match, new Date(), footballMatch.getScore()));
            }
        } else {
            LOGGER.error(String.format("Can't update the match %s score. The match was not found or already ended!", match));
            throw new MatchCommonException(ErrorMessageUtil.MATCH_NOT_FOUND);
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
        List<FootballMatch> footballMatches = footballTournament.getActiveMatches();
        if(footballMatches.contains(match)) {
            Set<FootballTeam> activeTeams = footballTournament.getActiveTeams();
            activeTeams.remove(match.getHomeTeam());
            activeTeams.remove(match.getAwayTeam());
            footballTournament.getActiveMatches().remove(match);
            LOGGER.info(String.format("Football Match %s ended at %s", match, new Date()));
        } else {
            LOGGER.error(String.format("Can't end the match %s. The match was not found or already ended!", match));
            throw new MatchCommonException(ErrorMessageUtil.MATCH_NOT_FOUND);
        }
    }

    @Override
    public void printScoreboard() {
        List<FootballMatch> sortedMatches = footballTournament.getSortedMatches();
        scoreboardCommonValidation(sortedMatches);
        if(sortedMatches.size() == 0){
            System.out.println(MatchMessageUtil.NO_ACTIVE_MATCHES);
            return;
        }
        IntStream.range(0, sortedMatches.size())
                .forEach(matchIndex -> {
                    FootballMatch match = sortedMatches.get(matchIndex);
                    System.out.println(matchIndex + 1 + ": " + match.toStringWithScore());
                });
    }

    @Override
    public List<String> getScoreboard() {
        List<FootballMatch> sortedMatches = footballTournament.getSortedMatches();
        scoreboardCommonValidation(sortedMatches);
        List<String> scoreboard = new ArrayList<>();
        if(sortedMatches.size() == 0){
            LOGGER.info("Currently there are no matches in progress");
            return scoreboard;
        }
        IntStream.range(0, sortedMatches.size())
                .forEach(matchIndex -> {
                    FootballMatch match = sortedMatches.get(matchIndex);
                    String scoreboardEntry = matchIndex + 1 + ": " + match.toStringWithScore();
                    scoreboard.add(scoreboardEntry);
                });
        return scoreboard;
    }

    private void scoreboardCommonValidation(List<FootballMatch> sortedMatches){
        if (sortedMatches == null){
            throw new MatchCommonException(ErrorMessageUtil.SCOREBOARD_NOT_NULL);
        }
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
