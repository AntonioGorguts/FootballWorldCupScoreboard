package scoreboard.football.model;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import scoreboard.exception.MatchCommonException;
import scoreboard.football.datagenerator.FootballComparatorDataGenerator;
import scoreboard.football.datagenerator.FootballMatchDataGenerator;
import scoreboard.football.processor.FootballTournamentProcessor;
import scoreboard.util.ErrorMessageUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(Enclosed.class)
public class FootballTournamentTest {

    public static class NotParametrizedPart {

        @Test
        public void shouldThrowExceptionWhenNullComparatorIsPassedToConstructor() {
            //GIVEN WHEN
            Comparator<FootballMatch> comparator = null;

            //THEN
            assertThrows(NullPointerException.class, () -> new FootballTournament(comparator));
        }

        @Test
        public void shouldCreateTournamentWithComparator() {
            //GIVEN WHEN
            Comparator<FootballMatch> comparator = Comparator.comparing(FootballMatch::getScoreTotal);
            FootballTournament footballTournament = new FootballTournament(comparator);

            //THEN
            assertEquals(footballTournament.getMatchComparator(), comparator);
        }

        @Test
        public void shouldCreateTournamentWithDefaultComparator() {
            //GIVEN WHEN
            FootballTournament footballTournament = new FootballTournament();

            //THEN
            assertNotNull(footballTournament.getMatchComparator());
        }

        @Test
        public void shouldThrowExceptionWhenNullComparatorIsPassedToSetter() {
            //GIVEN WHEN
            Comparator<FootballMatch> comparator = null;
            FootballTournament footballTournament = new FootballTournament();

            //THEN
            assertThrows(NullPointerException.class, () -> footballTournament.setMatchComparator(comparator));
        }

        @Test
        public void shouldSetComparatorToTournament() {
            //GIVEN WHEN
            Comparator<FootballMatch> comparator = Comparator.comparing(FootballMatch::getScoreTotal);
            FootballTournament footballTournament = new FootballTournament();
            footballTournament.setMatchComparator(comparator);

            //THEN
            assertEquals(footballTournament.getMatchComparator(), comparator);
        }

        @Test
        public void shouldSortMatchesWithComparator() {
            //GIVEN
            Comparator<FootballMatch> comparator = FootballComparatorDataGenerator.getDefaultComparator();
            FootballTournament footballTournament = spy(new FootballTournament(comparator));
            List<FootballMatch> activeMatches = FootballMatchDataGenerator.getMatchesWithoutDate();
            List<FootballMatch> matchesWithoutSort = FootballMatchDataGenerator.getMatchesWithoutDate();

            IntStream.range(0, activeMatches.size())
                    .forEach(matchIndex -> {
                        FootballMatch match = activeMatches.get(matchIndex);
                        match.getScore().setHomeScore(1);
                        match.setStartDate(new Date(System.currentTimeMillis() - matchIndex));
                    });

            //WHEN
            when(footballTournament.getActiveMatches()).thenReturn(activeMatches);

            //THEN
            FootballMatch firstMatch = footballTournament.getSortedMatches().get(0);
            FootballMatch lastMatch = footballTournament.getSortedMatches().get(footballTournament.getSortedMatches().size() - 1);
            assertEquals(firstMatch, matchesWithoutSort.get(matchesWithoutSort.size() - 1));
            assertEquals(lastMatch, matchesWithoutSort.get(0));
        }

        @Test
        public void shouldAddMatchToTheTournament(){
            //GIVEN
            FootballTournament footballTournament = new FootballTournament();
            FootballMatch match = FootballMatchDataGenerator.getAbsentFootballMatch();

            //WHEN
            footballTournament.addActiveMatch(match);

            //THEN
            assertTrue(footballTournament.getActiveMatches().contains(match));
        }

        @Test
        public void shouldRemoveMatchFromTheTournament(){
            //GIVEN
            FootballTournament footballTournament = new FootballTournament();
            FootballMatch match = FootballMatchDataGenerator.getAbsentFootballMatch();

            //WHEN
            footballTournament.addActiveMatch(match);
            footballTournament.removeActiveMatch(match);

            //THEN
            assertFalse(footballTournament.getActiveMatches().contains(match));
        }

        @Test
        public void shouldAddTeamToTheTournament(){
            //GIVEN
            FootballTournament footballTournament = new FootballTournament();
            FootballTeam team = new FootballTeam("Mexico");

            //WHEN
            footballTournament.addActiveTeam(team);

            //THEN
            assertTrue(footballTournament.getActiveTeams().contains(team));
        }

        @Test
        public void shouldRemoveTeamFromTheTournament(){
            //GIVEN
            FootballTournament footballTournament = new FootballTournament();
            FootballTeam team = new FootballTeam("Mexico");

            //WHEN
            footballTournament.addActiveTeam(team);
            footballTournament.removeActiveTeam(team);

            //THEN
            assertFalse(footballTournament.getActiveTeams().contains(team));
        }

        @Test
        public void shouldThrowExceptionWhenTeamIsAlreadyPlaying(){
            FootballTournament footballTournament = new FootballTournament();
            FootballTeam team = new FootballTeam("Mexico");

            //WHEN
            footballTournament.addActiveTeam(team);
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> footballTournament.addActiveTeam(team));

            //THEN
            assertEquals(ErrorMessageUtil.TEAM_ALREADY_PLAYING ,exception.getMessage());
        }

        @Test
        public void shouldThrowExceptionWhenMatchIsAlreadyInProgress(){
            //GIVEN
            FootballTournament footballTournament = new FootballTournament();
            FootballMatch match = FootballMatchDataGenerator.getAbsentFootballMatch();

            //WHEN
            footballTournament.addActiveMatch(match);
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> footballTournament.addActiveMatch(match));

            //THEN
            assertEquals(ErrorMessageUtil.MATCH_ALREADY_PLAYING, exception.getMessage());
        }

        @Test
        public void shouldThrowExceptionWhenTeamIsAbsent(){
            FootballTournament footballTournament = new FootballTournament();
            FootballTeam team = new FootballTeam("Mexico");

            //WHEN
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> footballTournament.removeActiveTeam(team));

            //THEN
            assertEquals(ErrorMessageUtil.TEAM_IS_ABSENT ,exception.getMessage());
        }

        @Test
        public void shouldThrowExceptionWhenMatchIsAbsent(){
            //GIVEN
            FootballTournament footballTournament = new FootballTournament();
            FootballMatch match = FootballMatchDataGenerator.getAbsentFootballMatch();

            //WHEN
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> footballTournament.removeActiveMatch(match));

            //THEN
            assertEquals(ErrorMessageUtil.MATCH_NOT_FOUND, exception.getMessage());
        }

    }

    @RunWith(Parameterized.class)
    public static class ParameterizedAddActiveMatchCheck{

        private final FootballTournament footballTournament = new FootballTournament();

        private String name;
        private FootballMatch match;
        private String errorMsg;

        public ParameterizedAddActiveMatchCheck(String name, FootballMatch match, String errorMsg){
            this.name = name;
            this.match = match;
            this.errorMsg = errorMsg;
        }

        @Test
        public void shouldReturnExceptionWhenMatchIsWrong(){
            //GIVEN WHEN
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> footballTournament.addActiveMatch(match));

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
            FootballMatch sameTeam = new FootballMatch(new FootballTeam("Test"), new FootballTeam("Test"));
            return Arrays.asList(new Object[][] {
                    {"should return exception when match is null", nullMatch, ErrorMessageUtil.MATCH_NOT_NULL},
                    {"should return exception when home team is null", homeNullTeam, ErrorMessageUtil.HOME_TEAM_NOT_BLANK},
                    {"should return exception when away team is null", awayNullTeam, ErrorMessageUtil.AWAY_TEAM_NOT_BLANK},
                    {"should return exception when home team is blank", homeBlankTeam, ErrorMessageUtil.HOME_TEAM_NOT_BLANK},
                    {"should return exception when away team is blank", awayBlankTeam, ErrorMessageUtil.AWAY_TEAM_NOT_BLANK},
                    {"should return exception when away team is same as home team", sameTeam, ErrorMessageUtil.SAME_HOME_AWAY_TEAM}
            });
        }
    }

    @RunWith(Parameterized.class)
    public static class ParameterizedRemoveActiveMatchCheck{

        private final FootballTournament footballTournament = new FootballTournament();

        private String name;
        private FootballMatch match;
        private String errorMsg;

        public ParameterizedRemoveActiveMatchCheck(String name, FootballMatch match, String errorMsg){
            this.name = name;
            this.match = match;
            this.errorMsg = errorMsg;
        }

        @Test
        public void shouldReturnExceptionWhenMatchIsWrong(){
            //GIVEN WHEN
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> footballTournament.removeActiveMatch(match));

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
            FootballMatch sameTeam = new FootballMatch(new FootballTeam("Test"), new FootballTeam("Test"));
            return Arrays.asList(new Object[][] {
                    {"should return exception when match is null", nullMatch, ErrorMessageUtil.MATCH_NOT_NULL},
                    {"should return exception when home team is null", homeNullTeam, ErrorMessageUtil.HOME_TEAM_NOT_BLANK},
                    {"should return exception when away team is null", awayNullTeam, ErrorMessageUtil.AWAY_TEAM_NOT_BLANK},
                    {"should return exception when home team is blank", homeBlankTeam, ErrorMessageUtil.HOME_TEAM_NOT_BLANK},
                    {"should return exception when away team is blank", awayBlankTeam, ErrorMessageUtil.AWAY_TEAM_NOT_BLANK},
                    {"should return exception when away team is same as home team", sameTeam, ErrorMessageUtil.SAME_HOME_AWAY_TEAM}
            });
        }
    }
}
