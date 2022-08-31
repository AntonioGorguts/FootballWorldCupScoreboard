package scoreboard.common.processor;

import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.model.score.TeamScore;

public interface TeamProcessor<TM extends TeamMatch, TS extends TeamScore> {
    void updateScore(TM match, TS score);
}
