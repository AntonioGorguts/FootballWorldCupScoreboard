package scoreboard.common.type;

import org.junit.Test;
import scoreboard.common.model.type.MatchType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MatchTypeTest {
    @Test
    public void shouldGetFootballMatchTypeByTypeName(){
        //GIVEN WHEN
        MatchType matchType = MatchType.getTypeByName("Football");

        //THEN
        assertEquals(matchType, MatchType.FOOTBALL);
    }

    @Test
    public void shouldGetFootballMatchTypeByTypeNameIgnoresCase(){
        //GIVEN WHEN
        MatchType matchType = MatchType.getTypeByName("FootbaLL");

        //THEN
        assertEquals(matchType, MatchType.FOOTBALL);
    }

    @Test
    public void shouldNotGetFootballMatchTypeByWrongTypeName(){
        //GIVEN WHEN
        MatchType matchType = MatchType.getTypeByName("Sooooccceerrr");

        //THEN
        assertNull(matchType);
    }

    @Test
    public void shouldNotGetMatchTypeByBlankName(){
        //GIVEN WHEN
        MatchType matchType = MatchType.getTypeByName(" ");

        //THEN
        assertNull(matchType);
    }
}
