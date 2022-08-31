package scoreboard.common.model.tournament;

import scoreboard.common.model.match.Match;

import java.util.List;

public interface Tournament<T extends Match> {
    List<T> getActiveMatches();
    List<T> getSortedMatches();
    void addActiveMatch(T match);
    void removeActiveMatch(T match);
}
