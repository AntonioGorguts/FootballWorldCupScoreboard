package scoreboard.football.model;

import scoreboard.common.model.tournament.TeamTournament;
import scoreboard.common.model.tournament.Tournament;

import java.util.List;
import java.util.Set;

public class FootballTournament implements TeamTournament<FootballTeam>, Tournament<FootballMatch> {
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
