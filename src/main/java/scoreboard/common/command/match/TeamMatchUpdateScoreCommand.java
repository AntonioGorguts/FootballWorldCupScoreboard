package scoreboard.common.command.match;

import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.model.score.TeamScore;
import scoreboard.common.processor.TeamTournamentProcessor;

public class TeamMatchUpdateScoreCommand extends TeamMatchCommand {
    private final TeamScore score;

    public TeamMatchUpdateScoreCommand(TeamTournamentProcessor tournamentProcessor, TeamMatch match, TeamScore score) {
        super(tournamentProcessor, match);
        this.score = score;
    }

    @Override
    public void execute() {
        tournamentProcessor.updateScore(match, score);
    }

    @Override
    public String toString() {
        return TeamMatchUpdateScoreCommand.class.getSimpleName()
                + " to match type " + match.getMatchType()
                + " match " + match +
                " with " + score.toString();
    }
}
