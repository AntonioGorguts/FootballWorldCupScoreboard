package scoreboard.common.command.match;

import scoreboard.common.command.tournament.TeamTournamentCommand;
import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.processor.TeamTournamentProcessor;

public abstract class TeamMatchCommandTeam extends TeamTournamentCommand {

    protected final TeamMatch match;

    protected TeamMatchCommandTeam(TeamTournamentProcessor tournamentProcessor, TeamMatch match) {
        super(tournamentProcessor);
        this.match = match;
    }
}
