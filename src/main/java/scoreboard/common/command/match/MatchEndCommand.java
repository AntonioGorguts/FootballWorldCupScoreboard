package scoreboard.common.command.match;

import scoreboard.common.command.match.MatchCommand;
import scoreboard.common.model.match.Match;
import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.model.score.TeamScore;
import scoreboard.common.processor.TeamTournamentProcessor;

public class MatchEndCommand extends MatchCommand {

    public MatchEndCommand(TeamTournamentProcessor tournamentProcessor, TeamMatch match) {
        super(tournamentProcessor, match);
    }

    @Override
    public void execute() {
        getTournamentProcessor().endMatch(getMatch());
    }
}
