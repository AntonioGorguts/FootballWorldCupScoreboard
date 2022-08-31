package scoreboard.common.model.tournament;

import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.model.team.Team;

import java.util.Set;

public abstract class TeamTournament<T extends Team, TM extends TeamMatch> implements Tournament<TM> {
    public abstract Set<T> getActiveTeams();
    protected abstract void addActiveTeam(T team);
    protected abstract void removeActiveTeam(T team);
}
