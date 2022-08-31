package scoreboard.common.factory.producer;

import scoreboard.common.factory.AbstractTeamSportFactory;
import scoreboard.common.model.type.MatchType;
import scoreboard.exception.MatchCommonException;
import scoreboard.util.ErrorMessageUtil;

public class FactoryProducer {
    public static AbstractTeamSportFactory getFactory(MatchType matchType){
        if(matchType == null){
            throw new MatchCommonException(ErrorMessageUtil.CANNOT_CREATE_FACTORY);
        }
        return matchType.getFactoryInstance();
    }

    public static AbstractTeamSportFactory getFactory(String matchTypeName){
        MatchType matchType = MatchType.getTypeByName(matchTypeName);
        if(matchType == null){
            throw new MatchCommonException(ErrorMessageUtil.CANNOT_CREATE_FACTORY);
        }
        return matchType.getFactoryInstance();
    }
}
