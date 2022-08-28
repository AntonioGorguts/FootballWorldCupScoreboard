package scoreboard.football.model;

import org.junit.Test;
import scoreboard.util.ErrorMessageUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class FootballScoreTest {

    @Test
    public void shouldNotAcceptNegativeValueForHomeScoreInConstructor() {
        //GIVEN WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new FootballScore(-2, 0));

        //THEN
        assertEquals(ErrorMessageUtil.INVALID_HOME_SCORE, exception.getMessage());
    }

    @Test
    public void shouldNotAcceptNegativeValueForAwayScoreInConstructor() {
        //GIVEN WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new FootballScore(0, -2));

        //THEN
        assertEquals(ErrorMessageUtil.INVALID_AWAY_SCORE, exception.getMessage());
    }

    @Test
    public void shouldNotAcceptNegativeValueForHomeScore() {
        //GIVEN
        FootballScore footballScore = new FootballScore(0, 0);

        //WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> footballScore.setHomeScore(-2));

        //THEN
        assertEquals(ErrorMessageUtil.INVALID_HOME_SCORE, exception.getMessage());
    }

    @Test
    public void shouldNotAcceptNegativeValueForAwayScore() {
        //GIVEN
        FootballScore footballScore = new FootballScore(0, 0);

        //WHEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> footballScore.setAwayScore(-2));

        //THEN
        assertEquals(ErrorMessageUtil.INVALID_AWAY_SCORE, exception.getMessage());
    }

    @Test
    public void shouldCreateScore() {
        //GIVEN WHEN
        FootballScore footballScore = new FootballScore(2, 0);

        //THEN
        assertEquals(footballScore.getHomeScore(), Integer.valueOf(2));
        assertEquals(footballScore.getAwayScore(), Integer.valueOf(0));
    }

    @Test
    public void shouldReturnScoreTotal() {
        //GIVEN
        FootballScore footballScore = new FootballScore(2, 0);

        //WHEN
        Integer scoreTotal = footballScore.getScoreTotal();

        //THEN
        assertEquals(scoreTotal, Integer.valueOf(2));
    }

    @Test
    public void shouldCompareDifferentScoreObjectsWithSameScore() {
        //GIVEN
        FootballScore footballScore = new FootballScore(2, 0);
        FootballScore anotherFootballScore = new FootballScore(2, 0);

        //WHEN
        boolean isEquals = footballScore.equals(anotherFootballScore);

        //THEN
        assertTrue(isEquals);
    }

    @Test
    public void shouldCompareDifferentScoreObjectsWithDifferentScore() {
        //GIVEN
        FootballScore footballScore = new FootballScore(2, 0);
        FootballScore anotherFootballScore = new FootballScore(0, 2);

        //WHEN
        boolean isEquals = footballScore.equals(anotherFootballScore);

        //THEN
        assertFalse(isEquals);
    }

    @Test
    public void shouldCompareDifferentScoreObjectsWithSameHash() {
        //GIVEN
        FootballScore footballScore = new FootballScore(2, 0);
        FootballScore anotherFootballScore = new FootballScore(2, 0);

        //WHEN
        boolean isEquals = footballScore.hashCode() == anotherFootballScore.hashCode();

        //THEN
        assertTrue(isEquals);
    }

    @Test
    public void shouldCompareDifferentScoreObjectsWithDifferentHash() {
        //GIVEN
        FootballScore footballScore = new FootballScore(2, 0);
        FootballScore anotherFootballScore = new FootballScore(0, 2);

        //WHEN
        boolean isEquals = footballScore.hashCode() == anotherFootballScore.hashCode();

        //THEN
        assertFalse(isEquals);
    }
}
