package scoreboard.common.command.match;

import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.model.score.TeamScore;
import scoreboard.common.processor.TeamTournamentProcessor;

public class TeamMatchUpdateScoreCommandTeam extends TeamMatchCommandTeam {
    private final TeamScore score;

    public TeamMatchUpdateScoreCommandTeam(TeamTournamentProcessor tournamentProcessor, TeamMatch match, TeamScore score) {
        super(tournamentProcessor, match);
        this.score = score;
    }

    @Override
    public void execute() {
        tournamentProcessor.updateScore(match, score);
    }
}
