package scoreboard.common.factory.producer;

import scoreboard.common.factory.AbstractTeamSportFactory;
import scoreboard.common.model.type.TeamMatchType;
import scoreboard.exception.MatchCommonException;
import scoreboard.util.ErrorMessageUtil;

public class TeamFactoryProducer {
    public static AbstractTeamSportFactory getFactory(TeamMatchType teamMatchType){
        if(teamMatchType == null){
            throw new MatchCommonException(ErrorMessageUtil.CANNOT_CREATE_FACTORY);
        }
        return teamMatchType.getFactoryInstance();
    }

    public static AbstractTeamSportFactory getFactory(String matchTypeName){
        TeamMatchType teamMatchType = TeamMatchType.getTypeByName(matchTypeName);
        if(teamMatchType == null){
            throw new MatchCommonException(ErrorMessageUtil.CANNOT_CREATE_FACTORY);
        }
        return teamMatchType.getFactoryInstance();
    }
}
