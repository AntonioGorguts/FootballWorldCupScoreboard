package scoreboard.football.processor;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import scoreboard.exception.MatchCommonException;
import scoreboard.football.model.FootballMatch;
import scoreboard.football.model.FootballScore;
import scoreboard.football.model.FootballTeam;
import scoreboard.football.model.FootballTournament;
import scoreboard.util.ErrorMessageUtil;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;

@RunWith(Enclosed.class)
public class FootballTournamentProcessorTest {
    public static class NotParametrizedPart {
        @Test
        public void ShouldStartMatch() {

        }

        @Test
        public void ShouldEndMatch() {

        }

        @Test
        public void ShouldUpdateScore() {

        }

        @Test
        public void ShouldPrintScoreboard() {

        }

        @Test
        public void ShouldExportScoreboard() {

        }
    }

//    @Test
//    public void ShouldThrowExceptionWhenMatchIsNull(){
//
//    }
//
//    @Test
//    public void ShouldThrowExceptionWhenHomeTeamIsNull(){
//
//    }
//
//    @Test
//    public void ShouldThrowExceptionWhenAwayTeamIsNull(){
//
//    }
//
//    private void checkMatchGeneralPart(){
//
//    }

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
