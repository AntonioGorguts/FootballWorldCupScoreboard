package scoreboard.football.command;

import org.junit.Test;
import scoreboard.football.command.match.FootballMatchEndCommand;
import scoreboard.football.command.match.FootballMatchStartCommand;
import scoreboard.football.command.match.FootballMatchUpdateScoreCommand;
import scoreboard.football.command.scoreboard.FootballScoreboardExportCommand;
import scoreboard.football.command.scoreboard.FootballScoreboardPrintCommand;
import scoreboard.football.processor.FootballTournamentProcessor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FootballCommandTest {

    private final FootballTournamentProcessor footballTournamentProcessor = mock(FootballTournamentProcessor.class);
    private final FootballTournamentCommandExecutor footballTournamentCommandExecutor = new FootballTournamentCommandExecutor();

    @Test
    public void shouldExecuteMatchStartCommand(){
        //GIVEN
        FootballMatchStartCommand matchStartCommand = spy(new FootballMatchStartCommand());

        //WHEN
        footballTournamentCommandExecutor.executeOperation(matchStartCommand);

        //THEN
        verify(matchStartCommand, times(1)).execute();
        verify(footballTournamentProcessor, times(1)).startMatch(any());
    }

    @Test
    public void shouldExecuteMatchEndCommand(){
        //GIVEN
        FootballMatchEndCommand matchEndCommand = spy(new FootballMatchEndCommand());

        //WHEN
        footballTournamentCommandExecutor.executeOperation(matchEndCommand);

        //THEN
        verify(matchEndCommand, times(1)).execute();
        verify(footballTournamentProcessor, times(1)).endMatch(any());
    }

    @Test
    public void shouldExecuteMatchUpdateScoreCommand(){
        //GIVEN
        FootballMatchUpdateScoreCommand matchUpdateScoreCommand = spy(new FootballMatchUpdateScoreCommand());

        //WHEN
        footballTournamentCommandExecutor.executeOperation(matchUpdateScoreCommand);

        //THEN
        verify(matchUpdateScoreCommand, times(1)).execute();
        verify(footballTournamentProcessor, times(1)).updateScore(any(), any());
    }

    @Test
    public void shouldExecuteScoreboardPrintCommand(){
        //GIVEN
        FootballScoreboardPrintCommand scoreboardPrintCommand = spy(new FootballScoreboardPrintCommand());

        //WHEN
        footballTournamentCommandExecutor.executeOperation(scoreboardPrintCommand);

        //THEN
        verify(scoreboardPrintCommand, times(1)).execute();
        verify(footballTournamentProcessor, times(1)).printScoreboard();
    }

    @Test
    public void shouldExecuteScoreboardExportCommand(){
        //GIVEN
        FootballScoreboardExportCommand scoreboardExportCommand = spy(new FootballScoreboardExportCommand());

        //WHEN
        footballTournamentCommandExecutor.executeOperation(scoreboardExportCommand);

        //THEN
        verify(scoreboardExportCommand, times(1)).execute();
        verify(footballTournamentProcessor, times(1)).getScoreboard();
    }
}
