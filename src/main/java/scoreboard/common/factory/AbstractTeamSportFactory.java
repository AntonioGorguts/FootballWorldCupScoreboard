package scoreboard.common.factory;


import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.model.score.TeamScore;
import scoreboard.common.model.team.Team;
import scoreboard.common.model.tournament.TeamTournament;
import scoreboard.common.processor.TeamTournamentProcessor;

import java.util.Comparator;

public interface AbstractTeamSportFactory<T extends Team,
        TM extends TeamMatch,
        TT extends TeamTournament<T, TM>,
        TS extends TeamScore,
        TTP extends TeamTournamentProcessor<TM, TS>> {
    T createTeam(String name);

    TM createMatch(T homeTeam, T awayTeam);

    TS createScore(int homeScore, int awayScore);

    TT createTournament(Comparator<TM> matchComparator);

    TTP createTournamentProcessor(TT tournament);
}
