package scoreboard.football.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.model.score.TeamScore;
import scoreboard.common.model.team.Team;

import java.util.Date;

public class FootballMatch implements TeamMatch {

    private final FootballTeam homeTeam;
    private final FootballTeam awayTeam;

    public FootballMatch(FootballTeam homeTeam, FootballTeam awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    @Override
    public Date getStartDate() {
        return null;
    }

    @Override
    public Team getHomeTeam() {
        return homeTeam;
    }

    @Override
    public Team getAwayTeam() {
        return awayTeam;
    }

    @Override
    public TeamScore getScore() {
        return null;
    }

    @Override
    public Integer getScoreTotal() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof FootballMatch)) return false;

        FootballMatch that = (FootballMatch) o;

        return new EqualsBuilder().append(homeTeam, that.homeTeam).append(awayTeam, that.awayTeam).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(homeTeam).append(awayTeam).toHashCode();
    }
}
