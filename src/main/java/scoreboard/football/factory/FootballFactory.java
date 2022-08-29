package scoreboard.football.factory;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import scoreboard.common.factory.AbstractTeamSportFactory;
import scoreboard.exception.MatchCommonException;
import scoreboard.football.model.FootballMatch;
import scoreboard.football.model.FootballScore;
import scoreboard.football.model.FootballTeam;
import scoreboard.util.ErrorMessageUtil;

public class FootballFactory implements AbstractTeamSportFactory<FootballTeam> {
    private static final Logger LOGGER = Logger.getLogger(FootballFactory.class);

    //Made no check for the length or special symbols because there were no business logic in the task for that
    @Override
    public FootballTeam createTeam(String name) {
        if(StringUtils.isBlank(name)){
            LOGGER.error("The team name should not be blank!");
            throw new MatchCommonException(ErrorMessageUtil.INVALID_TEAM_NAME);
        }
        return new FootballTeam(name);
    }

    @Override
    public FootballMatch createMatch(FootballTeam homeTeam, FootballTeam awayTeam) {
        if(homeTeam == null || StringUtils.isBlank(homeTeam.getName())){
            LOGGER.error("The home team name should not be blank!");
            throw new MatchCommonException(ErrorMessageUtil.INVALID_HOME_TEAM);
        }
        if(awayTeam == null || StringUtils.isBlank(awayTeam.getName())){
            LOGGER.error("The away team name should not be blank!");
            throw new MatchCommonException(ErrorMessageUtil.INVALID_AWAY_TEAM);
        }
        if(homeTeam.equals(awayTeam)){
            LOGGER.error(String.format("Home team value %s is the same as away team!", homeTeam.getName()));
            throw new MatchCommonException(ErrorMessageUtil.SAME_HOME_AWAY_TEAM);
        }
        return new FootballMatch(homeTeam, awayTeam);
    }

    @Override
    public FootballScore createScore(int homeScore, int awayScore) {
        if (homeScore < 0){
            LOGGER.error("The home team score should not be negative!");
            throw new MatchCommonException(ErrorMessageUtil.INVALID_HOME_SCORE);
        }
        if (awayScore < 0){
            LOGGER.error("The away team score should not be negative!");
            throw new MatchCommonException(ErrorMessageUtil.INVALID_AWAY_SCORE);
        }
        return new FootballScore(homeScore, awayScore);
    }
}
