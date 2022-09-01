package scoreboard.common.model.type;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import scoreboard.common.factory.AbstractTeamSportFactory;
import scoreboard.football.factory.FootballFactory;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

public enum TeamMatchType {
    FOOTBALL("Football", FootballFactory::new);

    private static final Logger LOGGER = Logger.getLogger(TeamMatchType.class);

    private final String name;
    private final Supplier<AbstractTeamSportFactory> factorySupplier;

    TeamMatchType(String name, Supplier<AbstractTeamSportFactory> factorySupplier) {
        this.factorySupplier = factorySupplier;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public AbstractTeamSportFactory getFactoryInstance() {
        return factorySupplier.get();
    }

    public static TeamMatchType getTypeByName(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        Optional<TeamMatchType> optionalTeamMatchType = Arrays.stream(values())
                .filter(teamMatchType -> teamMatchType.name.equalsIgnoreCase(name))
                .findAny();
        if (optionalTeamMatchType.isPresent()) {
            return optionalTeamMatchType.get();
        } else {
            LOGGER.info("There is no MatchType for this name: " + name);
            return null;
        }
    }
}

