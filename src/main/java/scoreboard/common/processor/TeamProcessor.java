package scoreboard.common.processor;

public interface TeamProcessor<TeamMatch, TeamScore> {
    void updateScore(TeamMatch match, TeamScore score);
}
