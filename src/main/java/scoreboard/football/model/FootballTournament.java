package scoreboard.football.model;

import scoreboard.common.model.tournament.TeamTournament;
import scoreboard.common.model.tournament.Tournament;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class FootballTournament implements TeamTournament<FootballTeam>, Tournament<FootballMatch> {

    private static final Comparator<FootballMatch> DEFAULT_COMPARATOR =
            Comparator.comparing(FootballMatch::getScoreTotal).reversed()
                    .thenComparing(FootballMatch::getStartDate);

    private final List<FootballMatch> activeMatches = new ArrayList<>();
    private final Set<FootballTeam> activeTeams = new HashSet<>();
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
    public List<FootballMatch> getActiveMatches() {
        return activeMatches;
    }

    @Override
    public List<FootballMatch> getSortedMatches() {
        //to leave the original list in original order
        List<FootballMatch> matches = new ArrayList<>(getActiveMatches());
        matches.sort(matchComparator);
        return matches;
    }

    @Override
    public Set<FootballTeam> getActiveTeams() {
        return activeTeams;
    }
}
