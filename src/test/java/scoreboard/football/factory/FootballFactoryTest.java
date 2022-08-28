package scoreboard.football.factory;

import org.junit.Test;
import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.model.score.TeamScore;
import scoreboard.common.model.team.Team;
import scoreboard.exception.MatchCommonException;
import scoreboard.football.model.FootballMatch;
import scoreboard.football.model.FootballScore;
import scoreboard.football.model.FootballTeam;
import scoreboard.util.ErrorMessageUtil;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class FootballFactoryTest {

    private final FootballFactory footballFactory = new FootballFactory();

    @Test
    public void shouldCreateFootballTeam(){
        //GIVEN
        String name = "testTeam";

        //WHEN
        Team footballTeam = footballFactory.createTeam(name);

        //THEN
        assertThat(footballTeam, instanceOf(FootballTeam.class));
        assertEquals(footballTeam.getName(), name);
    }

    @Test
    public void shouldCreateFootballMatch(){
        //GIVEN
        FootballTeam homeTeam = new FootballTeam("homeTeam");
        FootballTeam awayTeam = new FootballTeam("awayTeam");

        //WHEN
        TeamMatch footballMatch = footballFactory.createMatch(homeTeam, awayTeam);

        //THEN
        assertThat(footballMatch, instanceOf(FootballMatch.class));
        assertEquals(footballMatch.getHomeTeam(), homeTeam);
        assertEquals(footballMatch.getAwayTeam(), awayTeam);
    }

    @Test
    public void shouldThrowExceptionWhenFootballTeamNameIsNull(){
        //GIVEN
        String name = null;

        //WHEN
        MatchCommonException exception = assertThrows(MatchCommonException.class,
                () -> footballFactory.createTeam(name));

        //THEN
        assertEquals(ErrorMessageUtil.INVALID_TEAM_NAME, exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenFootballTeamNameIsBlank(){
        //GIVEN
        String name = " ";

        //WHEN
        MatchCommonException exception = assertThrows(MatchCommonException.class,
                () -> footballFactory.createTeam(name));

        //THEN
        assertEquals(ErrorMessageUtil.INVALID_TEAM_NAME, exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenFootballHomeTeamNameIsNull(){
        //GIVEN
        FootballTeam homeTeam = new FootballTeam(null);
        FootballTeam awayTeam = new FootballTeam("awayTeam");

        //WHEN
        MatchCommonException exception = assertThrows(MatchCommonException.class,
                () -> footballFactory.createMatch(homeTeam, awayTeam));

        //THEN
        assertEquals(ErrorMessageUtil.INVALID_HOME_TEAM, exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenFootballHomeTeamNameIsBlank(){
        //GIVEN
        FootballTeam homeTeam = new FootballTeam("");
        FootballTeam awayTeam = new FootballTeam("awayTeam");

        //WHEN
        MatchCommonException exception = assertThrows(MatchCommonException.class,
                () -> footballFactory.createMatch(homeTeam, awayTeam));

        //THEN
        assertEquals(ErrorMessageUtil.INVALID_HOME_TEAM, exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenFootballAwayTeamNameIsNull(){
        //GIVEN
        FootballTeam homeTeam = new FootballTeam("homeTeam");
        FootballTeam awayTeam = new FootballTeam(null);

        //WHEN
        MatchCommonException exception = assertThrows(MatchCommonException.class,
                () -> footballFactory.createMatch(homeTeam, awayTeam));

        //THEN
        assertEquals(ErrorMessageUtil.INVALID_AWAY_TEAM, exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenFootballAwayTeamNameIsBlank(){
        //GIVEN
        FootballTeam homeTeam = new FootballTeam("homeTeam");
        FootballTeam awayTeam = new FootballTeam("");

        //WHEN
        MatchCommonException exception = assertThrows(MatchCommonException.class,
                () -> footballFactory.createMatch(homeTeam, awayTeam));

        //THEN
        assertEquals(ErrorMessageUtil.INVALID_AWAY_TEAM, exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenHomeAndAwayTeamNameIsSame(){
        //GIVEN
        FootballTeam homeTeam = new FootballTeam("homeTeam");
        FootballTeam awayTeam = new FootballTeam("homeTeam");

        //WHEN
        MatchCommonException exception = assertThrows(MatchCommonException.class,
                () -> footballFactory.createMatch(homeTeam, awayTeam));

        //THEN
        assertEquals(ErrorMessageUtil.SAME_HOME_AWAY_TEAM, exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenHomeScoreIsNegative(){
        //GIVEN WHEN
        MatchCommonException exception = assertThrows(MatchCommonException.class,
                () -> footballFactory.createScore(-2, 0));

        //THEN
        assertEquals(ErrorMessageUtil.INVALID_HOME_SCORE, exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenAwayScoreIsNegative(){
        //GIVEN WHEN
        MatchCommonException exception = assertThrows(MatchCommonException.class,
                () -> footballFactory.createScore(0, -2));

        //THEN
        assertEquals(ErrorMessageUtil.INVALID_AWAY_SCORE, exception.getMessage());
    }

    @Test
    public void shouldCreateScore(){
        //GIVEN WHEN
        TeamScore footballScore = footballFactory.createScore(2, 0);

        //THEN
        assertThat(footballScore, instanceOf(FootballScore.class));
        assertEquals(footballScore.getHomeScore(), Integer.valueOf(2));
        assertEquals(footballScore.getAwayScore(), Integer.valueOf(0));
    }
}
