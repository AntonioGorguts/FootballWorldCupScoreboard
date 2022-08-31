package scoreboard.common.factory;

import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.model.score.TeamScore;
import scoreboard.common.model.team.Team;
import scoreboard.common.model.tournament.TeamTournament;
import scoreboard.common.processor.TeamTournamentProcessor;

public interface TournamentProcessorFactory<TS extends TeamScore, T extends Team, TM extends TeamMatch, TTP extends TeamTournamentProcessor<TM, TS>, TT extends TeamTournament<T, TM>> {
    TTP createTournamentProcessor(TT tournament);
}
