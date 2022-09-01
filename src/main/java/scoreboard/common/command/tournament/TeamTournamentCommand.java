package scoreboard.common.command.tournament;

import scoreboard.common.command.Command;
import scoreboard.common.processor.TeamTournamentProcessor;

public abstract class TeamTournamentCommand implements Command {

    protected final TeamTournamentProcessor tournamentProcessor;

    public TeamTournamentProcessor getTournamentProcessor(){
        return tournamentProcessor;
    }

    protected TeamTournamentCommand(TeamTournamentProcessor tournamentProcessor) {
        this.tournamentProcessor = tournamentProcessor;
    }
}
