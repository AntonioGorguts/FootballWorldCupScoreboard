package scoreboard.common.command.match;

import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.model.score.TeamScore;
import scoreboard.common.processor.TeamTournamentProcessor;

public class MatchUpdateScoreCommand extends MatchCommand {
    private final TeamScore score;

    public MatchUpdateScoreCommand(TeamTournamentProcessor tournamentProcessor, TeamMatch match, TeamScore score) {
        super(tournamentProcessor, match);
        this.score = score;
    }

    @Override
    public void execute() {
        getTournamentProcessor().updateScore(getMatch(), score);
    }
}
