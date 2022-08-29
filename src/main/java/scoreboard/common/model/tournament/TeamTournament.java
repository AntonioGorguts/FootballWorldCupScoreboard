package scoreboard.common.model.tournament;

import java.util.Set;

public abstract class TeamTournament<Team, Match> implements Tournament<Match> {
    public abstract Set<Team> getActiveTeams();
    protected abstract void addActiveTeam(Team team);
    protected abstract void removeActiveTeam(Team team);
}
