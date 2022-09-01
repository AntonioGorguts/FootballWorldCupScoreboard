package scoreboard.common.type;

import org.junit.Test;
import scoreboard.common.model.type.TeamMatchType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TeamMatchTypeTest {
    @Test
    public void shouldGetFootballMatchTypeByTypeName(){
        //GIVEN WHEN
        TeamMatchType teamMatchType = TeamMatchType.getTypeByName("Football");

        //THEN
        assertEquals(teamMatchType, TeamMatchType.FOOTBALL);
    }

    @Test
    public void shouldGetFootballMatchTypeByTypeNameIgnoresCase(){
        //GIVEN WHEN
        TeamMatchType teamMatchType = TeamMatchType.getTypeByName("FootbaLL");

        //THEN
        assertEquals(teamMatchType, TeamMatchType.FOOTBALL);
    }

    @Test
    public void shouldNotGetFootballMatchTypeByWrongTypeName(){
        //GIVEN WHEN
        TeamMatchType teamMatchType = TeamMatchType.getTypeByName("Sooooccceerrr");

        //THEN
        assertNull(teamMatchType);
    }

    @Test
    public void shouldNotGetMatchTypeByBlankName(){
        //GIVEN WHEN
        TeamMatchType teamMatchType = TeamMatchType.getTypeByName(" ");

        //THEN
        assertNull(teamMatchType);
    }
}
