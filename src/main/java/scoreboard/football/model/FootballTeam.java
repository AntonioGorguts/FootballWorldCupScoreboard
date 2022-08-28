package scoreboard.football.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import scoreboard.common.model.team.Team;

public class FootballTeam implements Team {

    private final String name;

    public FootballTeam(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof FootballTeam)) return false;

        FootballTeam that = (FootballTeam) o;

        return new EqualsBuilder().append(name, that.name).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(name).toHashCode();
    }
}
