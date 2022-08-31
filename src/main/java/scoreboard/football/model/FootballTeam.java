package scoreboard.football.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import scoreboard.common.model.team.Team;

//Made no setter for name because I guess there must be more complex logic to change the name of the team,
//like, for example, we need to check if it's participating in some tournaments and/or matches, etc.
public class FootballTeam extends Team {

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

        return new EqualsBuilder()
                .append(formatString(name), formatString(that.name))
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(formatString(name)).toHashCode();
    }

    private String formatString(String name){
        return StringUtils.lowerCase(StringUtils.trim(name));
    }
}
