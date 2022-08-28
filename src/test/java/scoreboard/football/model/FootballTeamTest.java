package scoreboard.football.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FootballTeamTest {

    @Test
    public void shouldCompareDifferentTeamObjectsWithSameName(){
        //GIVEN
        FootballTeam milanTeam = new FootballTeam("Milan");
        FootballTeam anotherMilanTeam = new FootballTeam("Milan");

        //WHEN
        boolean isEquals = milanTeam.equals(anotherMilanTeam);

        //THEN
        assertTrue(isEquals);
    }

    @Test
    public void shouldCompareDifferentTeamObjectsWithSameNameWithErrors(){
        //GIVEN
        FootballTeam milanTeam = new FootballTeam("Milan");
        FootballTeam anotherMilanTeam = new FootballTeam(" milAn ");

        //WHEN
        boolean isEquals = milanTeam.equals(anotherMilanTeam);

        //THEN
        assertTrue(isEquals);
    }

    @Test
    public void shouldCompareDifferentTeamObjectsWithDifferentName(){
        //GIVEN
        FootballTeam milanTeam = new FootballTeam("Milan");
        FootballTeam realTeam = new FootballTeam("Real");

        //WHEN
        boolean isEquals = milanTeam.equals(realTeam);

        //THEN
        assertFalse(isEquals);
    }

    @Test
    public void shouldCompareDifferentTeamObjectsWithSameHash(){
        //GIVEN
        FootballTeam milanTeam = new FootballTeam("Milan");
        FootballTeam anotherMilanTeam = new FootballTeam("Milan");

        //WHEN
        boolean isEquals = milanTeam.hashCode() == anotherMilanTeam.hashCode();

        //THEN
        assertTrue(isEquals);
    }

    @Test
    public void shouldCompareDifferentTeamObjectsWithSameHashWithErrors(){
        //GIVEN
        FootballTeam milanTeam = new FootballTeam("Milan");
        FootballTeam anotherMilanTeam = new FootballTeam(" milAn ");

        //WHEN
        boolean isEquals = milanTeam.hashCode() == anotherMilanTeam.hashCode();

        //THEN
        assertTrue(isEquals);
    }

    @Test
    public void shouldCompareDifferentTeamObjectsWithDifferentHash(){
        //GIVEN
        FootballTeam milanTeam = new FootballTeam("Milan");
        FootballTeam realTeam = new FootballTeam("Real");

        //WHEN
        boolean isEquals = milanTeam.hashCode() == realTeam.hashCode();

        //THEN
        assertFalse(isEquals);
    }
}
