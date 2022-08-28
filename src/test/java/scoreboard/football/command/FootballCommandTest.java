package scoreboard.football.command;

import org.junit.Test;
import scoreboard.football.command.match.FootballMatchEndCommand;
import scoreboard.football.command.match.FootballMatchStartCommand;
import scoreboard.football.command.match.FootballMatchUpdateScoreCommand;
import scoreboard.football.command.scoreboard.FootballScoreboardExportCommand;
import scoreboard.football.command.scoreboard.FootballScoreboardPrintCommand;
import scoreboard.football.datagenerator.FootballMatchDataGenerator;
import scoreboard.football.model.FootballMatch;
import scoreboard.football.model.FootballScore;
import scoreboard.football.processor.FootballTournamentProcessor;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FootballCommandTest {

    private final FootballTournamentProcessor footballTournamentProcessor = mock(FootballTournamentProcessor.class);
    private final FootballTournamentCommandExecutor footballTournamentCommandExecutor = new FootballTournamentCommandExecutor();

    @Test
    public void shouldExecuteMatchStartCommand(){
        //GIVEN
        FootballMatch match = FootballMatchDataGenerator.getPresentFootballMatch();
        FootballMatchStartCommand matchStartCommand = spy(new FootballMatchStartCommand(footballTournamentProcessor, match));

        //WHEN
        footballTournamentCommandExecutor.executeOperation(matchStartCommand);

        //THEN
        verify(matchStartCommand, times(1)).execute();
        verify(footballTournamentProcessor, times(1)).startMatch(any());
    }

    @Test
    public void shouldExecuteMatchEndCommand(){
        //GIVEN
        FootballMatch match = FootballMatchDataGenerator.getPresentFootballMatch();
        FootballMatchEndCommand matchEndCommand = spy(new FootballMatchEndCommand(footballTournamentProcessor, match));

        //WHEN
        footballTournamentCommandExecutor.executeOperation(matchEndCommand);

        //THEN
        verify(matchEndCommand, times(1)).execute();
        verify(footballTournamentProcessor, times(1)).endMatch(any());
    }

    @Test
    public void shouldExecuteMatchUpdateScoreCommand(){
        //GIVEN
        FootballMatch match = FootballMatchDataGenerator.getPresentFootballMatch();
        FootballScore score = new FootballScore(1,0);
        FootballMatchUpdateScoreCommand matchUpdateScoreCommand = spy(new FootballMatchUpdateScoreCommand(footballTournamentProcessor, match, score));

        //WHEN
        footballTournamentCommandExecutor.executeOperation(matchUpdateScoreCommand);

        //THEN
        verify(matchUpdateScoreCommand, times(1)).execute();
        verify(footballTournamentProcessor, times(1)).updateScore(any(), any());
    }

    @Test
    public void shouldExecuteScoreboardPrintCommand(){
        //GIVEN
        FootballScoreboardPrintCommand scoreboardPrintCommand = spy(new FootballScoreboardPrintCommand(footballTournamentProcessor));

        //WHEN
        footballTournamentCommandExecutor.executeOperation(scoreboardPrintCommand);

        //THEN
        verify(scoreboardPrintCommand, times(1)).execute();
        verify(footballTournamentProcessor, times(1)).printScoreboard();
    }

    @Test
    public void shouldExecuteScoreboardExportCommand(){
        //GIVEN
        FootballScoreboardExportCommand scoreboardExportCommand = spy(new FootballScoreboardExportCommand(footballTournamentProcessor));

        //WHEN
        footballTournamentCommandExecutor.executeOperation(scoreboardExportCommand);

        //THEN
        verify(scoreboardExportCommand, times(1)).execute();
        verify(footballTournamentProcessor, times(1)).getScoreboard();
    }

    @Test
    public void shouldScoreboardExportThroughCommand(){
        //GIVEN
        FootballScoreboardExportCommand scoreboardExportCommand = new FootballScoreboardExportCommand(footballTournamentProcessor);
        ArrayList<String> result = new ArrayList<>();
        result.add("Test1");
        result.add("Test2");

        //WHEN
        when(footballTournamentProcessor.getScoreboard()).thenReturn(result);
        footballTournamentCommandExecutor.executeOperation(scoreboardExportCommand);

        //THEN
        assertEquals(scoreboardExportCommand.getScoreboard(), result);
    }
}
