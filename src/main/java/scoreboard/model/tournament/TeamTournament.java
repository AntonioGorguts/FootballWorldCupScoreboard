package scoreboard.model.tournament;

import java.util.Set;

public interface TeamTournament<Team> {
    Set<Team> getActiveTeams();
}
