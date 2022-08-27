package scoreboard.football.factory;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import scoreboard.common.factory.TeamFactory;
import scoreboard.common.factory.TeamMatchFactory;
import scoreboard.exception.MatchCommonException;
import scoreboard.football.model.FootballMatch;
import scoreboard.football.model.FootballTeam;
import scoreboard.util.ErrorMessageUtil;

public class FootballFactory implements TeamFactory, TeamMatchFactory<FootballTeam> {
    private static final Logger LOGGER = Logger.getLogger(FootballFactory.class);

    //    Made no check for the length or special symbols because there were no business logic in the task for that
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
        return new FootballMatch(homeTeam, awayTeam);
    }
}
