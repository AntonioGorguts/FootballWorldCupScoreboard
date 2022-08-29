package scoreboard.football.processor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import scoreboard.exception.MatchCommonException;
import scoreboard.football.datagenerator.FootballMatchDataGenerator;
import scoreboard.football.datagenerator.FootballTeamDataGenerator;
import scoreboard.football.model.FootballMatch;
import scoreboard.football.model.FootballScore;
import scoreboard.football.model.FootballTeam;
import scoreboard.football.model.FootballTournament;
import scoreboard.util.ErrorMessageUtil;
import scoreboard.util.MatchMessageUtil;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(Enclosed.class)
public class FootballTournamentProcessorTest {
    public static class NotParametrizedPart {

        private final FootballTournament footballTournament = mock(FootballTournament.class);

        private final FootballTournamentProcessor footballTournamentProcessor = new FootballTournamentProcessor(footballTournament);

        @Test
        public void shouldUpdateScoreAndNotChangeMatchAndTeamList() {
            //GIVEN
            FootballMatch footballMatch = FootballMatchDataGenerator.getPresentFootballMatch();
            List<FootballMatch> activeMatches = FootballMatchDataGenerator.getActiveMatches();
            Set<FootballTeam> activeFootballTeams = FootballTeamDataGenerator.getActiveFootballTeams();
            FootballScore footballScore = new FootballScore(2,0);

            //WHEN
            when(footballTournament.getActiveMatches()).thenReturn(activeMatches);
            when(footballTournament.getActiveTeams()).thenReturn(activeFootballTeams);
            footballTournamentProcessor.updateScore(footballMatch, footballScore);

            //THEN
            assertTrue(activeFootballTeams.contains(footballMatch.getHomeTeam()));
            assertTrue(activeFootballTeams.contains(footballMatch.getAwayTeam()));
            assertTrue(activeMatches.contains(footballMatch));
            assertEquals(activeFootballTeams.size(), FootballTeamDataGenerator.getActiveFootballTeams().size());
            assertEquals(activeMatches.size(), FootballMatchDataGenerator.getActiveMatches().size());
        }

        @Test
        public void shouldUpdateScore() {
            //GIVEN
            FootballMatch footballMatch = FootballMatchDataGenerator.getPresentFootballMatch();
            List<FootballMatch> activeMatches = FootballMatchDataGenerator.getActiveMatches();
            Set<FootballTeam> activeFootballTeams = FootballTeamDataGenerator.getActiveFootballTeams();
            FootballScore footballScore = new FootballScore(2,0);

            //WHEN
            when(footballTournament.getActiveMatches()).thenReturn(activeMatches);
            when(footballTournament.getActiveTeams()).thenReturn(activeFootballTeams);
            footballTournamentProcessor.updateScore(footballMatch, footballScore);

            //THEN
            FootballMatch updatedMatch = activeMatches.get(activeMatches.indexOf(footballMatch));
            FootballScore updatedMatchScore = updatedMatch.getScore();

            assertEquals(updatedMatchScore.getScoreTotal(), Integer.valueOf(2));
            assertEquals(updatedMatch.getScoreTotal(), Integer.valueOf(2));
            assertEquals(updatedMatchScore.getHomeScore(), Integer.valueOf(2));
            assertEquals(updatedMatchScore.getAwayScore(), Integer.valueOf(0));
        }

        @Test
        public void shouldNotThrowExceptionWhenHomeUpdateScoreIsTheSame() {
            //GIVEN
            FootballMatch footballMatch = FootballMatchDataGenerator.getPresentFootballMatch();
            List<FootballMatch> activeMatches = FootballMatchDataGenerator.getActiveMatches();
            Set<FootballTeam> activeFootballTeams = FootballTeamDataGenerator.getActiveFootballTeams();
            FootballScore footballScore = new FootballScore(2,0);

            //WHEN
            when(footballTournament.getActiveMatches()).thenReturn(activeMatches);
            when(footballTournament.getActiveTeams()).thenReturn(activeFootballTeams);
            footballTournamentProcessor.updateScore(footballMatch, footballScore);
            footballTournamentProcessor.updateScore(footballMatch, footballScore);

            //THEN
            FootballMatch updatedMatch = activeMatches.get(activeMatches.indexOf(footballMatch));
            FootballScore updatedMatchScore = updatedMatch.getScore();

            assertEquals(updatedMatchScore.getScoreTotal(), Integer.valueOf(2));
            assertEquals(updatedMatch.getScoreTotal(), Integer.valueOf(2));
            assertEquals(updatedMatchScore.getHomeScore(), Integer.valueOf(2));
            assertEquals(updatedMatchScore.getAwayScore(), Integer.valueOf(0));
        }

        @Test
        public void shouldNotThrowExceptionWhenAwayUpdateScoreIsTheSame() {
            //GIVEN
            FootballMatch footballMatch = FootballMatchDataGenerator.getPresentFootballMatch();
            List<FootballMatch> activeMatches = FootballMatchDataGenerator.getActiveMatches();
            Set<FootballTeam> activeFootballTeams = FootballTeamDataGenerator.getActiveFootballTeams();
            FootballScore footballScore = new FootballScore(0,2);

            //WHEN
            when(footballTournament.getActiveMatches()).thenReturn(activeMatches);
            when(footballTournament.getActiveTeams()).thenReturn(activeFootballTeams);
            footballTournamentProcessor.updateScore(footballMatch, footballScore);
            footballTournamentProcessor.updateScore(footballMatch, footballScore);

            //THEN
            FootballMatch updatedMatch = activeMatches.get(activeMatches.indexOf(footballMatch));
            FootballScore updatedMatchScore = updatedMatch.getScore();

            assertEquals(updatedMatchScore.getScoreTotal(), Integer.valueOf(2));
            assertEquals(updatedMatch.getScoreTotal(), Integer.valueOf(2));
            assertEquals(updatedMatchScore.getHomeScore(), Integer.valueOf(0));
            assertEquals(updatedMatchScore.getAwayScore(), Integer.valueOf(2));
        }

        @Test
        public void shouldExportScoreboard() {
            //GIVEN
            List<FootballMatch> activeMatches = FootballMatchDataGenerator.getActiveMatches();

            //WHEN
            when(footballTournament.getSortedMatches()).thenReturn(activeMatches);
            List<String> scoreboard = footballTournamentProcessor.getScoreboard();

            //THEN
            assertEquals(scoreboard.size(), 5);
            assertTrue(scoreboard.get(0).contains(activeMatches.get(0).toStringWithScore()));
            assertTrue(scoreboard.get(scoreboard.size()-1).contains(activeMatches.get(activeMatches.size()-1)
                    .toStringWithScore()));
        }

        @Test
        public void shouldExportEmptyScoreboardWhenThereAreNoActiveMatches() {
            //GIVEN WHEN
            when(footballTournament.getSortedMatches()).thenReturn(new ArrayList<>());
            List<String> scoreboard = footballTournamentProcessor.getScoreboard();

            //THEN
            assertEquals(scoreboard.size(), 0);
        }

        @Test
        public void shouldThrowExceptionAndNotUpdateScoreWhenScoreIsNull() {
            //GIVEN
            FootballMatch footballMatch = FootballMatchDataGenerator.getPresentFootballMatch();
            FootballScore footballScore = null;

            //WHEN
            MatchCommonException exception = assertThrows(MatchCommonException.class,
                    () -> footballTournamentProcessor.updateScore(footballMatch, footballScore));

            //THEN
            assertEquals(ErrorMessageUtil.SCORE_NOT_NULL, exception.getMessage());
        }

        @Test
        public void shouldThrowExceptionAndNotUpdateScoreWhenHomeScoreIsLesserThanPrevious() {
            //GIVEN
            FootballMatch footballMatch = FootballMatchDataGenerator.getPresentFootballMatch();
            List<FootballMatch> activeMatches = FootballMatchDataGenerator.getActiveMatches();
            Set<FootballTeam> activeFootballTeams = FootballTeamDataGenerator.getActiveFootballTeams();
            FootballScore footballScore = new FootballScore(2,0);

            //WHEN
            when(footballTournament.getActiveMatches()).thenReturn(activeMatches);
            when(footballTournament.getActiveTeams()).thenReturn(activeFootballTeams);
            footballTournamentProcessor.updateScore(footballMatch, footballScore);

            FootballScore newScore = new FootballScore(1,0);

            //WHEN
            MatchCommonException exception = assertThrows(MatchCommonException.class,
                    () -> footballTournamentProcessor.updateScore(footballMatch, newScore));

            //THEN
            assertEquals(ErrorMessageUtil.HOME_SCORE_IS_LESSER_THAN_BEFORE, exception.getMessage());
        }

        @Test
        public void shouldThrowExceptionAndNotUpdateScoreWhenAwayScoreIsLesserThanPrevious() {
            //GIVEN
            FootballMatch footballMatch = FootballMatchDataGenerator.getPresentFootballMatch();
            List<FootballMatch> activeMatches = FootballMatchDataGenerator.getActiveMatches();
            Set<FootballTeam> activeFootballTeams = FootballTeamDataGenerator.getActiveFootballTeams();
            FootballScore footballScore = new FootballScore(0,2);

            //WHEN
            when(footballTournament.getActiveMatches()).thenReturn(activeMatches);
            when(footballTournament.getActiveTeams()).thenReturn(activeFootballTeams);
            footballTournamentProcessor.updateScore(footballMatch, footballScore);

            FootballScore newScore = new FootballScore(1,1);

            //WHEN
            MatchCommonException exception = assertThrows(MatchCommonException.class,
                    () -> footballTournamentProcessor.updateScore(footballMatch, newScore));

            //THEN
            assertEquals(ErrorMessageUtil.AWAY_SCORE_IS_LESSER_THAN_BEFORE, exception.getMessage());
        }

        @Test
        public void shouldThrowExceptionAndNotUpdateScoreWhenMatchIsNotFound() {
            //GIVEN
            FootballMatch footballMatch = FootballMatchDataGenerator.getAbsentFootballMatch();
            FootballScore footballScore = new FootballScore(1,1);
            List<FootballMatch> activeMatches = FootballMatchDataGenerator.getActiveMatches();

            //WHEN
            when(footballTournament.getActiveMatches()).thenReturn(activeMatches);
            MatchCommonException exception = assertThrows(MatchCommonException.class,
                    () -> footballTournamentProcessor.updateScore(footballMatch, footballScore));

            //THEN
            assertEquals(ErrorMessageUtil.MATCH_NOT_FOUND, exception.getMessage());
        }

        @Test
        public void shouldThrowExceptionAndNotEndMatchWhenMatchIsNotFound() {
            //GIVEN
            FootballMatch footballMatch = FootballMatchDataGenerator.getAbsentFootballMatch();
            List<FootballMatch> activeMatches = FootballMatchDataGenerator.getActiveMatches();

            //WHEN
            when(footballTournament.getActiveMatches()).thenReturn(activeMatches);
            MatchCommonException exception = assertThrows(MatchCommonException.class,
                    () -> footballTournamentProcessor.endMatch(footballMatch));

            //THEN
            assertEquals(ErrorMessageUtil.MATCH_NOT_FOUND, exception.getMessage());
        }

        @Test
        public void shouldThrowExceptionAndNotStartMatchWhenMatchIsAlreadyInProgress() {
            //GIVEN
            FootballMatch footballMatch = FootballMatchDataGenerator.getPresentFootballMatch();
            List<FootballMatch> activeMatches = FootballMatchDataGenerator.getActiveMatches();

            //WHEN
            when(footballTournament.getActiveMatches()).thenReturn(activeMatches);
            MatchCommonException exception = assertThrows(MatchCommonException.class,
                    () -> footballTournamentProcessor.startMatch(footballMatch));

            //THEN
            assertEquals(ErrorMessageUtil.MATCH_ALREADY_PLAYING, exception.getMessage());
        }

        @Test
        public void shouldThrowExceptionAndNotStartMatchWhenWhenHomeAndAwayTeamNameIsSame(){
            //GIVEN
            FootballTeam homeTeam = new FootballTeam("homeTeam");
            FootballTeam awayTeam = new FootballTeam("homeTeam");
            FootballMatch footballMatch = new FootballMatch(homeTeam, awayTeam);

            //WHEN
            MatchCommonException exception = assertThrows(MatchCommonException.class,
                    () -> footballTournamentProcessor.startMatch(footballMatch));

            //THEN
            assertEquals(ErrorMessageUtil.SAME_HOME_AWAY_TEAM, exception.getMessage());
        }

        @Test
        public void shouldThrowExceptionAndNotStartMatchWhenWhenHomeAndAwayTeamNameIsSameWithErrors(){
            //GIVEN
            FootballTeam homeTeam = new FootballTeam("homeTeam");
            FootballTeam awayTeam = new FootballTeam(" hometeam ");
            FootballMatch footballMatch = new FootballMatch(homeTeam, awayTeam);

            //WHEN
            MatchCommonException exception = assertThrows(MatchCommonException.class,
                    () -> footballTournamentProcessor.startMatch(footballMatch));

            //THEN
            assertEquals(ErrorMessageUtil.SAME_HOME_AWAY_TEAM, exception.getMessage());
        }

        @Test
        public void shouldThrowExceptionAndNotStartMatchWhenActiveHomeTeamIsAlreadyPlaying() {
            //GIVEN
            FootballTeam homeTeam = new FootballTeam("Australia");
            FootballTeam awayTeam = new FootballTeam("Ukraine");
            FootballMatch footballMatch = new FootballMatch(homeTeam, awayTeam);
            List<FootballMatch> activeMatches = FootballMatchDataGenerator.getActiveMatches();
            Set<FootballTeam> activeFootballTeams = FootballTeamDataGenerator.getActiveFootballTeams();

            //WHEN
            when(footballTournament.getActiveMatches()).thenReturn(activeMatches);
            when(footballTournament.getActiveTeams()).thenReturn(activeFootballTeams);
            MatchCommonException exception = assertThrows(MatchCommonException.class,
                    () -> footballTournamentProcessor.startMatch(footballMatch));

            //THEN
            assertEquals(ErrorMessageUtil.HOME_TEAM_ALREADY_PLAYING, exception.getMessage());
        }

        @Test
        public void shouldThrowExceptionAndNotStartMatchWhenActiveAwayTeamIsAlreadyPlaying() {
            //GIVEN
            FootballTeam homeTeam = new FootballTeam("Ukraine");
            FootballTeam awayTeam = new FootballTeam("Australia");
            FootballMatch footballMatch = new FootballMatch(homeTeam, awayTeam);
            List<FootballMatch> activeMatches = FootballMatchDataGenerator.getActiveMatches();
            Set<FootballTeam> activeFootballTeams = FootballTeamDataGenerator.getActiveFootballTeams();

            //WHEN
            when(footballTournament.getActiveMatches()).thenReturn(activeMatches);
            when(footballTournament.getActiveTeams()).thenReturn(activeFootballTeams);
            MatchCommonException exception = assertThrows(MatchCommonException.class,
                    () -> footballTournamentProcessor.startMatch(footballMatch));

            //THEN
            assertEquals(ErrorMessageUtil.AWAY_TEAM_ALREADY_PLAYING, exception.getMessage());
        }

        @Test
        public void shouldThrowExceptionAndStartMatchWhenDateIsAfterNow() {
            //GIVEN
            FootballMatch footballMatch = FootballMatchDataGenerator.getAbsentFootballMatchWithoutDate();
            List<FootballMatch> activeMatches = FootballMatchDataGenerator.getActiveMatches();
            footballMatch.setStartDate(new Date(System.currentTimeMillis() + 100000));

            //WHEN
            when(footballTournament.getActiveMatches()).thenReturn(activeMatches);
            MatchCommonException exception = assertThrows(MatchCommonException.class,
                    () -> footballTournamentProcessor.startMatch(footballMatch));

            //THEN
            assertEquals(ErrorMessageUtil.MATCH_DATE_IS_IN_FUTURE, exception.getMessage());
        }

        @Test
        public void shouldThrowExceptionWhenSortedMatchesAreNullInPrint(){
            //GIVEN WHEN
            when(footballTournament.getSortedMatches()).thenReturn(null);
            MatchCommonException exception = assertThrows(MatchCommonException.class,
                    footballTournamentProcessor::printScoreboard);

            //THEN
            assertEquals(ErrorMessageUtil.SCOREBOARD_NOT_NULL, exception.getMessage());
        }

        @Test
        public void shouldThrowExceptionWhenSortedMatchesAreNullInExport(){
            //GIVEN WHEN
            when(footballTournament.getSortedMatches()).thenReturn(null);
            MatchCommonException exception = assertThrows(MatchCommonException.class,
                    footballTournamentProcessor::getScoreboard);

            //THEN
            assertEquals(ErrorMessageUtil.SCOREBOARD_NOT_NULL, exception.getMessage());
        }
    }

    public static class TournamentWithSpyPart {
        private final FootballTournament footballTournament = spy(new FootballTournament());

        private final FootballTournamentProcessor footballTournamentProcessor = new FootballTournamentProcessor(footballTournament);

        @Test
        public void shouldStartMatchAndUpdateActiveMatchAndTeamList() {
            //GIVEN
            FootballMatch footballMatch = FootballMatchDataGenerator.getAbsentFootballMatch();

            //WHEN
            footballTournamentProcessor.startMatch(footballMatch);

            //THEN
            Set<FootballTeam> activeFootballTeams = footballTournament.getActiveTeams();
            List<FootballMatch> activeMatches = footballTournament.getActiveMatches();

            assertNotNull(footballTournamentProcessor.getFootballTournament());
            assertTrue(activeFootballTeams.contains(footballMatch.getHomeTeam()));
            assertTrue(activeFootballTeams.contains(footballMatch.getAwayTeam()));
            assertTrue(activeMatches.contains(footballMatch));
        }

        @Test
        public void shouldEndMatchAndUpdateActiveMatchAndTeamList() {
            //GIVEN
            FootballMatch footballMatch = FootballMatchDataGenerator.getAbsentFootballMatch();

            //WHEN
            footballTournamentProcessor.startMatch(footballMatch);
            footballTournamentProcessor.endMatch(footballMatch);

            //THEN
            List<FootballMatch> activeMatches = footballTournament.getActiveMatches();
            Set<FootballTeam> activeFootballTeams = footballTournament.getActiveTeams();

            assertNotNull(footballTournamentProcessor.getFootballTournament());
            assertFalse(activeFootballTeams.contains(footballMatch.getHomeTeam()));
            assertFalse(activeFootballTeams.contains(footballMatch.getAwayTeam()));
            assertFalse(activeMatches.contains(footballMatch));
        }

        @Test
        public void shouldNotAddMatchToActiveMatchesManually() {
            //GIVEN
            FootballMatch footballMatch = FootballMatchDataGenerator.getAbsentFootballMatch();

            //WHEN
            footballTournamentProcessor.getFootballTournament().getActiveMatches().add(footballMatch);

            //THEN
            List<FootballMatch> activeMatches = footballTournament.getActiveMatches();

            assertFalse(activeMatches.contains(footballMatch));
        }

        @Test
        public void shouldNotAddTeamToActiveTeamsManually() {
            //GIVEN
            FootballTeam team = new FootballTeam("Mexico");

            //WHEN
            footballTournamentProcessor.getFootballTournament().getActiveTeams().add(team);

            //THEN
            Set<FootballTeam> activeFootballTeams = footballTournament.getActiveTeams();

            assertFalse(activeFootballTeams.contains(team));
        }

        @Test
        public void shouldStartMatchWithoutDateAndUpdateActiveMatchAndTeamList() {
            //GIVEN
            FootballMatch footballMatch = FootballMatchDataGenerator.getAbsentFootballMatchWithoutDate();

            //WHEN
            footballTournamentProcessor.startMatch(footballMatch);

            //THEN
            Set<FootballTeam> activeFootballTeams = footballTournament.getActiveTeams();
            List<FootballMatch> activeMatches = footballTournament.getActiveMatches();

            assertNotNull(footballTournamentProcessor.getFootballTournament());
            assertNotNull(footballMatch.getStartDate());
            assertTrue(activeFootballTeams.contains(footballMatch.getHomeTeam()));
            assertTrue(activeFootballTeams.contains(footballMatch.getAwayTeam()));
            assertTrue(activeMatches.contains(footballMatch));
        }
    }

    public static class ScoreboardProcessorPrintPart {
        private final FootballTournament footballTournament = mock(FootballTournament.class);

        private final FootballTournamentProcessor footballTournamentProcessor = new FootballTournamentProcessor(footballTournament);

        private final PrintStream standardOut = System.out;
        private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

        @Before
        public void setUp() {
            System.setOut(new PrintStream(outputStreamCaptor));
        }

        @Test
        public void shouldPrintScoreboard() {
            //GIVEN
            List<FootballMatch> activeMatches = FootballMatchDataGenerator.getActiveMatches();

            //WHEN
            when(footballTournament.getSortedMatches()).thenReturn(activeMatches);
            footballTournamentProcessor.printScoreboard();

            //THEN
            assertTrue(outputStreamCaptor.toString().trim().length() > 0);
            assertTrue(outputStreamCaptor.toString().trim().contains(activeMatches.get(0).toStringWithScore()));
            assertTrue(outputStreamCaptor.toString().trim().contains(activeMatches.get(activeMatches.size() - 1)
                    .toStringWithScore()));
        }

        @Test
        public void shouldPrintMessageAboutEmptyScoreboard() {
            //GIVEN WHEN
            when(footballTournament.getSortedMatches()).thenReturn(new ArrayList<>());
            footballTournamentProcessor.printScoreboard();

            //THEN
            assertTrue(outputStreamCaptor.toString().trim().length() > 0);
            assertEquals(outputStreamCaptor.toString().trim(), MatchMessageUtil.NO_ACTIVE_MATCHES);
        }

        @After
        public void tearDown() {
            System.setOut(standardOut);
        }
    }

    @RunWith(Parameterized.class)
    public static class ParameterizedStartMatchCheck{

        private final FootballTournament footballTournament = mock(FootballTournament.class);

        private final FootballTournamentProcessor footballTournamentProcessor = new FootballTournamentProcessor(footballTournament);

        private String name;
        private FootballMatch match;
        private String errorMsg;

        public ParameterizedStartMatchCheck(String name, FootballMatch match, String errorMsg){
            this.name = name;
            this.match = match;
            this.errorMsg = errorMsg;
        }

        @Test
        public void shouldReturnExceptionWhenMatchIsWrong(){
            //GIVEN WHEN
            MatchCommonException exception = assertThrows(MatchCommonException.class,
                    () -> footballTournamentProcessor.startMatch(match));

            //THEN
            assertEquals(errorMsg, exception.getMessage());
        }

        @Parameterized.Parameters(name = "{0}")
        public static Collection<Object[]> data() {
            FootballMatch nullMatch = null;
            FootballMatch homeNullTeam = new FootballMatch(null, new FootballTeam("Test"));
            FootballMatch awayNullTeam = new FootballMatch(new FootballTeam("Test"), null);
            FootballMatch homeBlankTeam = new FootballMatch(new FootballTeam(""), new FootballTeam("Test"));
            FootballMatch awayBlankTeam = new FootballMatch(new FootballTeam("Test"), new FootballTeam(""));
            return Arrays.asList(new Object[][] {
                    {"should return exception when match is null", nullMatch, ErrorMessageUtil.MATCH_NOT_NULL},
                    {"should return exception when home team is null", homeNullTeam, ErrorMessageUtil.HOME_TEAM_NOT_BLANK},
                    {"should return exception when away team is null", awayNullTeam, ErrorMessageUtil.AWAY_TEAM_NOT_BLANK},
                    {"should return exception when home team is blank", homeBlankTeam, ErrorMessageUtil.HOME_TEAM_NOT_BLANK},
                    {"should return exception when away team is blank", awayBlankTeam, ErrorMessageUtil.AWAY_TEAM_NOT_BLANK}
            });
        }
    }

    @RunWith(Parameterized.class)
    public static class ParameterizedEndMatchCheck{
        private final FootballTournament footballTournament = mock(FootballTournament.class);

        private final FootballTournamentProcessor footballTournamentProcessor = new FootballTournamentProcessor(footballTournament);

        private String name;
        private FootballMatch match;
        private String errorMsg;

        public ParameterizedEndMatchCheck(String name, FootballMatch match, String errorMsg){
            this.name = name;
            this.match = match;
            this.errorMsg = errorMsg;
        }

        @Test
        public void shouldReturnExceptionWhenMatchIsWrong(){
            //GIVEN WHEN
            MatchCommonException exception = assertThrows(MatchCommonException.class,
                    () -> footballTournamentProcessor.endMatch(match));

            //THEN
            assertEquals(errorMsg, exception.getMessage());
        }

        @Parameterized.Parameters(name = "{0}")
        public static Collection<Object[]> data() {
            FootballMatch nullMatch = null;
            FootballMatch homeNullTeam = new FootballMatch(null, new FootballTeam("Test"));
            FootballMatch awayNullTeam = new FootballMatch(new FootballTeam("Test"), null);
            FootballMatch homeBlankTeam = new FootballMatch(new FootballTeam(""), new FootballTeam("Test"));
            FootballMatch awayBlankTeam = new FootballMatch(new FootballTeam("Test"), new FootballTeam(""));
            return Arrays.asList(new Object[][] {
                    {"should return exception when match is null", nullMatch, ErrorMessageUtil.MATCH_NOT_NULL},
                    {"should return exception when home team is null", homeNullTeam, ErrorMessageUtil.HOME_TEAM_NOT_BLANK},
                    {"should return exception when away team is null", awayNullTeam, ErrorMessageUtil.AWAY_TEAM_NOT_BLANK},
                    {"should return exception when home team is blank", homeBlankTeam, ErrorMessageUtil.HOME_TEAM_NOT_BLANK},
                    {"should return exception when away team is blank", awayBlankTeam, ErrorMessageUtil.AWAY_TEAM_NOT_BLANK}
            });
        }
    }

    @RunWith(Parameterized.class)
    public static class ParameterizedUpdateScoreMatchCheck{
        private final FootballTournament footballTournament = mock(FootballTournament.class);

        private final FootballTournamentProcessor footballTournamentProcessor = new FootballTournamentProcessor(footballTournament);

        private String name;
        private FootballMatch match;
        private String errorMsg;

        public ParameterizedUpdateScoreMatchCheck(String name, FootballMatch match, String errorMsg){
            this.name = name;
            this.match = match;
            this.errorMsg = errorMsg;
        }

        @Test
        public void shouldReturnExceptionWhenMatchIsWrong(){
            //GIVEN
            FootballScore footballScore = new FootballScore(0, 0);

            // WHEN
            MatchCommonException exception = assertThrows(MatchCommonException.class,
                    () -> footballTournamentProcessor.updateScore(match, footballScore));

            //THEN
            assertEquals(errorMsg, exception.getMessage());
        }

        @Parameterized.Parameters(name = "{0}")
        public static Collection<Object[]> data() {
            FootballMatch nullMatch = null;
            FootballMatch homeNullTeam = new FootballMatch(null, new FootballTeam("Test"));
            FootballMatch awayNullTeam = new FootballMatch(new FootballTeam("Test"), null);
            FootballMatch homeBlankTeam = new FootballMatch(new FootballTeam(""), new FootballTeam("Test"));
            FootballMatch awayBlankTeam = new FootballMatch(new FootballTeam("Test"), new FootballTeam(""));
            return Arrays.asList(new Object[][] {
                    {"should return exception when match is null", nullMatch, ErrorMessageUtil.MATCH_NOT_NULL},
                    {"should return exception when home team is null", homeNullTeam, ErrorMessageUtil.HOME_TEAM_NOT_BLANK},
                    {"should return exception when away team is null", awayNullTeam, ErrorMessageUtil.AWAY_TEAM_NOT_BLANK},
                    {"should return exception when home team is blank", homeBlankTeam, ErrorMessageUtil.HOME_TEAM_NOT_BLANK},
                    {"should return exception when away team is blank", awayBlankTeam, ErrorMessageUtil.AWAY_TEAM_NOT_BLANK}
            });
        }
    }
}
