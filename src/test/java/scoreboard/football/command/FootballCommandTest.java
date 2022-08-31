package scoreboard.football.command;

import org.junit.Test;
import scoreboard.common.command.match.MatchEndCommand;
import scoreboard.common.command.match.MatchStartCommand;
import scoreboard.common.command.match.MatchUpdateScoreCommand;
import scoreboard.common.command.scoreboard.ScoreboardExportCommand;
import scoreboard.common.command.scoreboard.ScoreboardPrintCommand;
import scoreboard.common.command.tournament.TournamentCommandExecutor;
import scoreboard.football.datagenerator.FootballMatchDataGenerator;
import scoreboard.football.model.FootballMatch;
import scoreboard.football.model.FootballScore;
import scoreboard.football.processor.FootballTeamTournamentProcessor;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FootballCommandTest {

    private final FootballTeamTournamentProcessor footballTournamentProcessor = mock(FootballTeamTournamentProcessor.class);
    private final TournamentCommandExecutor tournamentCommandExecutor = new TournamentCommandExecutor();

    @Test
    public void shouldExecuteMatchStartCommand(){
        //GIVEN
        FootballMatch match = FootballMatchDataGenerator.getPresentFootballMatch();
        MatchStartCommand matchStartCommand = spy(new MatchStartCommand(footballTournamentProcessor, match));

        //WHEN
        tournamentCommandExecutor.executeOperation(matchStartCommand);

        //THEN
        verify(matchStartCommand, times(1)).execute();
        verify(footballTournamentProcessor, times(1)).startMatch(any());
    }

    @Test
    public void shouldExecuteMatchEndCommand(){
        //GIVEN
        FootballMatch match = FootballMatchDataGenerator.getPresentFootballMatch();
        MatchEndCommand matchEndCommand = spy(new MatchEndCommand(footballTournamentProcessor, match));

        //WHEN
        tournamentCommandExecutor.executeOperation(matchEndCommand);

        //THEN
        verify(matchEndCommand, times(1)).execute();
        verify(footballTournamentProcessor, times(1)).endMatch(any());
    }

    @Test
    public void shouldExecuteMatchUpdateScoreCommand(){
        //GIVEN
        FootballMatch match = FootballMatchDataGenerator.getPresentFootballMatch();
        FootballScore score = new FootballScore(1,0);
        MatchUpdateScoreCommand matchUpdateScoreCommand = spy(new MatchUpdateScoreCommand(footballTournamentProcessor, match, score));

        //WHEN
        tournamentCommandExecutor.executeOperation(matchUpdateScoreCommand);

        //THEN
        verify(matchUpdateScoreCommand, times(1)).execute();
        verify(footballTournamentProcessor, times(1)).updateScore(any(), any());
    }

    @Test
    public void shouldExecuteScoreboardPrintCommand(){
        //GIVEN
        ScoreboardPrintCommand scoreboardPrintCommand = spy(new ScoreboardPrintCommand(footballTournamentProcessor));

        //WHEN
        tournamentCommandExecutor.executeOperation(scoreboardPrintCommand);

        //THEN
        verify(scoreboardPrintCommand, times(1)).execute();
        verify(footballTournamentProcessor, times(1)).printScoreboard();
    }

    @Test
    public void shouldExecuteScoreboardExportCommand(){
        //GIVEN
        ScoreboardExportCommand scoreboardExportCommand = spy(new ScoreboardExportCommand(footballTournamentProcessor));

        //WHEN
        tournamentCommandExecutor.executeOperation(scoreboardExportCommand);

        //THEN
        verify(scoreboardExportCommand, times(1)).execute();
        verify(footballTournamentProcessor, times(1)).getScoreboard();
    }

    @Test
    public void shouldScoreboardExportThroughCommand(){
        //GIVEN
        ScoreboardExportCommand scoreboardExportCommand = new ScoreboardExportCommand(footballTournamentProcessor);
        ArrayList<String> result = new ArrayList<>();
        result.add("Test1");
        result.add("Test2");

        //WHEN
        when(footballTournamentProcessor.getScoreboard()).thenReturn(result);
        tournamentCommandExecutor.executeOperation(scoreboardExportCommand);

        //THEN
        assertEquals(scoreboardExportCommand.getScoreboard(), result);
    }
}
