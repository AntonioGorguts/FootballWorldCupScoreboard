package scoreboard.common.model.tournament;

import scoreboard.common.model.match.Match;

import java.util.List;

public interface Tournament<M extends Match> {
    List<M> getActiveMatches();
    List<M> getSortedMatches();
    void addActiveMatch(M match);
    void removeActiveMatch(M match);
}
