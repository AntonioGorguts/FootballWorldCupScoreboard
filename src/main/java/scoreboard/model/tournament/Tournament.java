package scoreboard.model.tournament;

import java.util.List;

public interface Tournament<Match> {
    List<Match> getActiveMatches();
    List<Match> getSortedMatches();
}
