package scoreboard.football.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.model.score.TeamScore;
import scoreboard.common.model.team.Team;
import scoreboard.util.ErrorMessageUtil;

import java.util.Date;
import java.util.Objects;

public class FootballMatch implements TeamMatch {

    private Date startDate;
    private final FootballScore score;
    private final FootballTeam homeTeam;
    private final FootballTeam awayTeam;

    public FootballMatch(FootballTeam homeTeam, FootballTeam awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        score = new FootballScore(0, 0);
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        if (this.startDate != null) {
            throw new IllegalStateException(ErrorMessageUtil.START_DATE_WAS_ALREADY_SET);
        }
        this.startDate = Objects.requireNonNull(startDate);
    }

    @Override
    public FootballTeam getHomeTeam() {
        return homeTeam;
    }

    @Override
    public FootballTeam getAwayTeam() {
        return awayTeam;
    }

    @Override
    public FootballScore getScore() {
        return score;
    }

    @Override
    public Integer getScoreTotal() {
        return score.getScoreTotal();
    }

    @Override
    public String toStringWithScore() {
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
