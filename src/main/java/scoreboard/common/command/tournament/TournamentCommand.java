package scoreboard.common.command.tournament;

import scoreboard.common.command.Command;
import scoreboard.common.processor.TeamTournamentProcessor;

public abstract class TournamentCommand implements Command {

    private final TeamTournamentProcessor tournamentProcessor;

    public TeamTournamentProcessor getTournamentProcessor(){
        return tournamentProcessor;
    }

    public TournamentCommand(TeamTournamentProcessor tournamentProcessor) {
        this.tournamentProcessor = tournamentProcessor;
    }
}
