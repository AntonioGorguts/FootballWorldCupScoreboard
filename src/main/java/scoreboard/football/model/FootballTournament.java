package scoreboard.football.model;

import scoreboard.common.model.tournament.TeamTournament;
import scoreboard.common.model.tournament.Tournament;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class FootballTournament implements TeamTournament<FootballTeam>, Tournament<FootballMatch> {

    private static final Comparator<FootballMatch> DEFAULT_COMPARATOR =
            Comparator.comparing(FootballMatch::getScoreTotal).reversed()
                    .thenComparing(FootballMatch::getStartDate);

    private Comparator<FootballMatch> matchComparator;

    public FootballTournament() {
        matchComparator = DEFAULT_COMPARATOR;
    }

    public FootballTournament(Comparator<FootballMatch> matchComparator) {
        this.matchComparator = Objects.requireNonNull(matchComparator);
    }

    public Comparator<FootballMatch> getMatchComparator() {
        return matchComparator;
    }

    public void setMatchComparator(Comparator<FootballMatch> matchComparator) {
        this.matchComparator = Objects.requireNonNull(matchComparator);
    }

    @Override
    public Set<FootballTeam> getActiveTeams() {
        return null;
    }

    @Override
    public List<FootballMatch> getActiveMatches() {
        return null;
    }

    @Override
    public List<FootballMatch> getSortedMatches() {
        return null;
    }
}
