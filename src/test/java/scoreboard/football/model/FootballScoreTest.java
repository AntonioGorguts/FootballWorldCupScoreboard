package scoreboard.football.model;

import org.junit.Test;
import scoreboard.util.ErrorMessageUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class FootballScoreTest {

    @Test
    public void shouldNotAcceptNegativeValueForHomeScore(){
        //GIVEN WHEN
        FootballScore footballScore = new FootballScore();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () ->  footballScore.setHomeScore(-2));

                        //THEN
        assertEquals(ErrorMessageUtil.INVALID_HOME_SCORE, exception.getMessage());
    }

    @Test
    public void shouldNotAcceptNegativeValueForAwayScore(){
        //GIVEN WHEN
        FootballScore footballScore = new FootballScore();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () ->  footballScore.setAwayScore(-2));

        //THEN
        assertEquals(ErrorMessageUtil.INVALID_AWAY_SCORE, exception.getMessage());
    }

    @Test
    public void shouldReturnScoreTotal(){
        //GIVEN
        FootballScore footballScore = new FootballScore();
        footballScore.setHomeScore(2);
        footballScore.setAwayScore(0);

        //WHEN
        Integer scoreTotal = footballScore.getScoreTotal();

        //THEN
        assertEquals(scoreTotal, Integer.valueOf(2));
    }
}
