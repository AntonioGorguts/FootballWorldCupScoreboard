package scoreboard.common.model.type;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import scoreboard.common.factory.AbstractTeamSportFactory;
import scoreboard.common.factory.producer.FactoryProducer;
import scoreboard.common.model.match.Match;
import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.model.score.TeamScore;
import scoreboard.common.model.team.Team;
import scoreboard.common.model.tournament.TeamTournament;
import scoreboard.common.processor.TeamTournamentProcessor;
import scoreboard.football.factory.FootballFactory;
import scoreboard.football.model.FootballMatch;
import scoreboard.football.model.FootballScore;
import scoreboard.football.model.FootballTeam;
import scoreboard.football.model.FootballTournament;
import scoreboard.football.processor.FootballTeamTournamentProcessor;

import java.util.function.Supplier;

public enum MatchType {
    FOOTBALL("Football", new FootballFactory());

    private static final Logger LOGGER = Logger.getLogger(MatchType.class);

    private final String name;
    private final AbstractTeamSportFactory factoryInstance;

    MatchType(String name, AbstractTeamSportFactory factoryInstance) {
        this.factoryInstance = factoryInstance;
        this.name = name;
    }

    private String getName(){
        return name;
    }

    public AbstractTeamSportFactory getFactoryInstance(){
        return factoryInstance;
    }

    public static MatchType getTypeByName(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        if(name.equalsIgnoreCase(FOOTBALL.getName())){
            return FOOTBALL;
        } else {
            LOGGER.info("There is no MatchType for this name: " + name);
            return null;
        }
    }
}

