package scoreboard.football.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FootballMatchTest {

    @Test
    public void shouldCompareDifferentMatchObjectsWithSameTeams(){
        //GIVEN
        FootballTeam milanHomeTeam = new FootballTeam("Milan");
        FootballTeam realHomeTeam = new FootballTeam("Real");

        FootballTeam anotherMilanHomeTeam = new FootballTeam("Milan");
        FootballTeam anotherRealHomeTeam = new FootballTeam("Real");

        FootballMatch footballMatch = new FootballMatch(milanHomeTeam, realHomeTeam);
        FootballMatch anotherFootballMatch = new FootballMatch(anotherMilanHomeTeam, anotherRealHomeTeam);

        //WHEN
        boolean isEquals = footballMatch.equals(anotherFootballMatch);

        //THEN
        assertTrue(isEquals);
    }

    @Test
    public void shouldCompareDifferentMatchObjectsWithSameHash(){
        //GIVEN
        FootballTeam milanHomeTeam = new FootballTeam("Milan");
        FootballTeam realHomeTeam = new FootballTeam("Real");

        FootballTeam anotherMilanHomeTeam = new FootballTeam("Milan");
        FootballTeam anotherRealHomeTeam = new FootballTeam("Real");

        FootballMatch footballMatch = new FootballMatch(milanHomeTeam, realHomeTeam);
        FootballMatch anotherFootballMatch = new FootballMatch(anotherMilanHomeTeam, anotherRealHomeTeam);

        //WHEN
        boolean isEquals = footballMatch.hashCode() == anotherFootballMatch.hashCode();

        //THEN
        assertTrue(isEquals);
    }

    @Test
    public void shouldCompareDifferentMatchObjectsWithDifferentTeams(){
        //GIVEN
        FootballTeam milanHomeTeam = new FootballTeam("Milan");
        FootballTeam realHomeTeam = new FootballTeam("Real");

        FootballTeam anotherMilanHomeTeam = new FootballTeam("Milan-2");
        FootballTeam anotherRealHomeTeam = new FootballTeam("Real-2");

        FootballMatch footballMatch = new FootballMatch(milanHomeTeam, realHomeTeam);
        FootballMatch anotherFootballMatch = new FootballMatch(anotherMilanHomeTeam, anotherRealHomeTeam);

        //WHEN
        boolean isEquals = footballMatch.equals(anotherFootballMatch);

        //THEN
        assertFalse(isEquals);
    }

    @Test
    public void shouldCompareDifferentMatchObjectsWithDifferentHash(){
        //GIVEN
        FootballTeam milanHomeTeam = new FootballTeam("Milan");
        FootballTeam realHomeTeam = new FootballTeam("Real");

        FootballTeam anotherMilanHomeTeam = new FootballTeam("Milan-2");
        FootballTeam anotherRealHomeTeam = new FootballTeam("Real-2");

        FootballMatch footballMatch = new FootballMatch(milanHomeTeam, realHomeTeam);
        FootballMatch anotherFootballMatch = new FootballMatch(anotherMilanHomeTeam, anotherRealHomeTeam);

        //WHEN
        boolean isEquals = footballMatch.hashCode() == anotherFootballMatch.hashCode();

        //THEN
        assertFalse(isEquals);
    }

}
