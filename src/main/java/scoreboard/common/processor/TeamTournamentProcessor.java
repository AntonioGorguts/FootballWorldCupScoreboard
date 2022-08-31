package scoreboard.common.processor;

import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.model.score.TeamScore;

public abstract class TeamTournamentProcessor<TM extends TeamMatch, TS extends TeamScore> implements TeamProcessor<TM, TS>, ScoreboardProcessor{
    public abstract void startMatch(TM match);
    public abstract void endMatch(TM match);
}
