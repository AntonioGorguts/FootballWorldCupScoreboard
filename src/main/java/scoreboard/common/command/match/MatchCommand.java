package scoreboard.common.command.match;

import scoreboard.common.command.tournament.TournamentCommand;
import scoreboard.common.model.match.Match;
import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.model.score.TeamScore;
import scoreboard.common.processor.TeamTournamentProcessor;

public abstract class MatchCommand extends TournamentCommand {
    private final TeamMatch match;

    public MatchCommand(TeamTournamentProcessor tournamentProcessor, TeamMatch match) {
        super(tournamentProcessor);
        this.match = match;
    }

    public TeamMatch getMatch() {
        return match;
    }
}
