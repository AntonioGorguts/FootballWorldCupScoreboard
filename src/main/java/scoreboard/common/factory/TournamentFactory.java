package scoreboard.common.factory;

import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.model.team.Team;
import scoreboard.common.model.tournament.TeamTournament;

import java.util.Comparator;

public interface TournamentFactory<C extends Team, T extends TeamTournament<C, Z>, Z extends TeamMatch> {
    T createTournament(Comparator<Z> matchComparator);
}
