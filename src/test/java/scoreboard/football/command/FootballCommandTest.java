package scoreboard.football.command;

import org.junit.Test;
import scoreboard.common.command.match.TeamMatchEndCommandTeam;
import scoreboard.common.command.match.TeamMatchStartCommandTeam;
import scoreboard.common.command.match.TeamMatchUpdateScoreCommandTeam;
import scoreboard.common.command.scoreboard.TeamScoreboardExportCommand;
import scoreboard.common.command.scoreboard.TeamScoreboardPrintCommand;
import scoreboard.common.command.tournament.TeamTournamentCommandExecutor;
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
    private final TeamTournamentCommandExecutor teamTournamentCommandExecutor = new TeamTournamentCommandExecutor();

    @Test
    public void shouldExecuteMatchStartCommand(){
        //GIVEN
        FootballMatch match = FootballMatchDataGenerator.getPresentFootballMatch();
        TeamMatchStartCommandTeam matchStartCommand = spy(new TeamMatchStartCommandTeam(footballTournamentProcessor, match));

        //WHEN
        teamTournamentCommandExecutor.executeOperation(matchStartCommand);

        //THEN
        verify(matchStartCommand, times(1)).execute();
        verify(footballTournamentProcessor, times(1)).startMatch(any());
    }

    @Test
    public void shouldExecuteMatchEndCommand(){
        //GIVEN
        FootballMatch match = FootballMatchDataGenerator.getPresentFootballMatch();
        TeamMatchEndCommandTeam matchEndCommand = spy(new TeamMatchEndCommandTeam(footballTournamentProcessor, match));

        //WHEN
        teamTournamentCommandExecutor.executeOperation(matchEndCommand);

        //THEN
        verify(matchEndCommand, times(1)).execute();
        verify(footballTournamentProcessor, times(1)).endMatch(any());
    }

    @Test
    public void shouldExecuteMatchUpdateScoreCommand(){
        //GIVEN
        FootballMatch match = FootballMatchDataGenerator.getPresentFootballMatch();
        FootballScore score = new FootballScore(1,0);
        TeamMatchUpdateScoreCommandTeam matchUpdateScoreCommand = spy(new TeamMatchUpdateScoreCommandTeam(footballTournamentProcessor, match, score));

        //WHEN
        teamTournamentCommandExecutor.executeOperation(matchUpdateScoreCommand);

        //THEN
        verify(matchUpdateScoreCommand, times(1)).execute();
        verify(footballTournamentProcessor, times(1)).updateScore(any(), any());
    }

    @Test
    public void shouldExecuteScoreboardPrintCommand(){
        //GIVEN
        TeamScoreboardPrintCommand scoreboardPrintCommand = spy(new TeamScoreboardPrintCommand(footballTournamentProcessor));

        //WHEN
        teamTournamentCommandExecutor.executeOperation(scoreboardPrintCommand);

        //THEN
        verify(scoreboardPrintCommand, times(1)).execute();
        verify(footballTournamentProcessor, times(1)).printScoreboard();
    }

    @Test
    public void shouldExecuteScoreboardExportCommand(){
        //GIVEN
        TeamScoreboardExportCommand scoreboardExportCommand = spy(new TeamScoreboardExportCommand(footballTournamentProcessor));

        //WHEN
        teamTournamentCommandExecutor.executeOperation(scoreboardExportCommand);

        //THEN
        verify(scoreboardExportCommand, times(1)).execute();
        verify(footballTournamentProcessor, times(1)).getScoreboard();
    }

    @Test
    public void shouldScoreboardExportThroughCommand(){
        //GIVEN
        TeamScoreboardExportCommand scoreboardExportCommand = new TeamScoreboardExportCommand(footballTournamentProcessor);
        ArrayList<String> result = new ArrayList<>();
        result.add("Test1");
        result.add("Test2");

        //WHEN
        when(footballTournamentProcessor.getScoreboard()).thenReturn(result);
        teamTournamentCommandExecutor.executeOperation(scoreboardExportCommand);

        //THEN
        assertEquals(scoreboardExportCommand.getScoreboard(), result);
    }
}
