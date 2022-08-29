package scoreboard.common.model.tournament;

import java.util.List;

public interface Tournament<Match> {
    List<Match> getActiveMatches();
    List<Match> getSortedMatches();
    void addActiveMatch(Match match);
    void removeActiveMatch(Match match);
}
