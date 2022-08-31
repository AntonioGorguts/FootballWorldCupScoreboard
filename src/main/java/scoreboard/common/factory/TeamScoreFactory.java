package scoreboard.common.factory;

import scoreboard.common.model.score.TeamScore;

public interface TeamScoreFactory {
    TeamScore createScore(int homeScore, int awayScore);
}
