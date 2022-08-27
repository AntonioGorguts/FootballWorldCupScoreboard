package scoreboard.football.model;

import scoreboard.common.model.tournament.TeamTournament;
import scoreboard.common.model.tournament.Tournament;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class FootballTournament implements TeamTournament<FootballTeam>, Tournament<FootballMatch> {

    private Comparator<FootballMatch> matchComparator;

    public FootballTournament(){

    }

    public FootballTournament(Comparator<FootballMatch> matchComparator) {
        this.matchComparator = Objects.requireNonNull(matchComparator);
    }

    public Comparator<FootballMatch> getMatchComparator() {
        return matchComparator;
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
