package scoreboard.football.model;

import org.junit.Test;
import scoreboard.football.datagenerator.FootballMatchDataGenerator;
import scoreboard.util.ErrorMessageUtil;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class FootballMatchTest {

    @Test
    public void shouldCompareDifferentMatchObjectsWithSameTeams(){
        //GIVEN
        FootballTeam milanHomeTeam = new FootballTeam("Milan");
        FootballTeam realAwayTeam = new FootballTeam("Real");

        FootballTeam anotherMilanHomeTeam = new FootballTeam("Milan");
        FootballTeam anotherRealAwayTeam = new FootballTeam("Real");

        FootballMatch footballMatch = new FootballMatch(milanHomeTeam, realAwayTeam);
        FootballMatch anotherFootballMatch = new FootballMatch(anotherMilanHomeTeam, anotherRealAwayTeam);

        //WHEN
        boolean isEquals = footballMatch.equals(anotherFootballMatch);

        //THEN
        assertTrue(isEquals);
    }

    @Test
    public void shouldCompareDifferentMatchObjectsWithSameHash(){
        //GIVEN
        FootballTeam milanHomeTeam = new FootballTeam("Milan");
        FootballTeam realAwayTeam = new FootballTeam("Real");

        FootballTeam anotherMilanHomeTeam = new FootballTeam("Milan");
        FootballTeam anotherRealAwayTeam = new FootballTeam("Real");

        FootballMatch footballMatch = new FootballMatch(milanHomeTeam, realAwayTeam);
        FootballMatch anotherFootballMatch = new FootballMatch(anotherMilanHomeTeam, anotherRealAwayTeam);

        //WHEN
        boolean isEquals = footballMatch.hashCode() == anotherFootballMatch.hashCode();

        //THEN
        assertTrue(isEquals);
    }

    @Test
    public void shouldCompareDifferentMatchObjectsWithDifferentTeams(){
        //GIVEN
        FootballTeam milanHomeTeam = new FootballTeam("Milan");
        FootballTeam realAwayTeam = new FootballTeam("Real");

        FootballTeam anotherMilanHomeTeam = new FootballTeam("Milan-2");
        FootballTeam anotherRealAwayTeam = new FootballTeam("Real-2");

        FootballMatch footballMatch = new FootballMatch(milanHomeTeam, realAwayTeam);
        FootballMatch anotherFootballMatch = new FootballMatch(anotherMilanHomeTeam, anotherRealAwayTeam);

        //WHEN
        boolean isEquals = footballMatch.equals(anotherFootballMatch);

        //THEN
        assertFalse(isEquals);
    }

    @Test
    public void shouldCompareDifferentMatchObjectsWithDifferentHash(){
        //GIVEN
        FootballTeam milanHomeTeam = new FootballTeam("Milan");
        FootballTeam realAwayTeam = new FootballTeam("Real");

        FootballTeam anotherMilanHomeTeam = new FootballTeam("Milan-2");
        FootballTeam anotherRealAwayTeam = new FootballTeam("Real-2");

        FootballMatch footballMatch = new FootballMatch(milanHomeTeam, realAwayTeam);
        FootballMatch anotherFootballMatch = new FootballMatch(anotherMilanHomeTeam, anotherRealAwayTeam);

        //WHEN
        boolean isEquals = footballMatch.hashCode() == anotherFootballMatch.hashCode();

        //THEN
        assertFalse(isEquals);
    }

    @Test
    public void shouldCreateMatchWithZeroScoreByDefault(){
        //GIVEN
        FootballMatch match = FootballMatchDataGenerator.getFootballMatch();

        //WHEN
        FootballScore score = match.getScore();

        //THEN
        assertEquals(score.getHomeScore(), Integer.valueOf(0));
        assertEquals(score.getAwayScore(), Integer.valueOf(0));
    }

    @Test
    public void shouldThrowExceptionWhenDateIsNull(){
        //GIVEN
        FootballMatch match = FootballMatchDataGenerator.getFootballMatch();

        //WHEN THEN
        assertThrows(NullPointerException.class, () -> match.setStartDate(null));
    }

    @Test
    public void shouldThrowExceptionWhenDateIsAlreadySet() {
        //GIVEN
        FootballMatch match = FootballMatchDataGenerator.getFootballMatch();
        match.setStartDate(new Date());

        //WHEN
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> match.setStartDate(new Date()));

        //THEN
        assertEquals(ErrorMessageUtil.START_DATE_WAS_ALREADY_SET, exception.getMessage());
    }

}
