package scoreboard.common.factory;

import org.junit.Test;
import scoreboard.common.factory.producer.TeamFactoryProducer;
import scoreboard.common.model.type.TeamMatchType;
import scoreboard.exception.MatchCommonException;
import scoreboard.football.factory.FootballFactory;
import scoreboard.util.ErrorMessageUtil;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TeamFactoryProducerTest {
    @Test
    public void shouldCreateFootballTournamentByType(){
        //GIVEN WHEN
        AbstractTeamSportFactory factory = TeamFactoryProducer.getFactory(TeamMatchType.FOOTBALL);

        //THEN
        assertThat(factory, instanceOf(FootballFactory.class));
    }

    @Test
    public void shouldCreateFootballTournamentByTypeName(){
        //GIVEN WHEN
        AbstractTeamSportFactory factory = TeamFactoryProducer.getFactory("Football");

        //THEN
        assertThat(factory, instanceOf(FootballFactory.class));
    }

    @Test
    public void shouldCreateFootballTournamentByTypeNameIgnoresCase(){
        //GIVEN WHEN
        AbstractTeamSportFactory factory = TeamFactoryProducer.getFactory("fOOtball");

        //THEN
        assertThat(factory, instanceOf(FootballFactory.class));
    }

    @Test
    public void shouldNotCreateFootballTournamentByNullType(){
        //GIVEN
        TeamMatchType teamMatchType = null;

        //WHEN
        MatchCommonException exception = assertThrows(MatchCommonException.class,
                () -> TeamFactoryProducer.getFactory(teamMatchType));

        //THEN
        assertEquals(exception.getMessage(), ErrorMessageUtil.CANNOT_CREATE_FACTORY);
    }

    @Test
    public void shouldNotCreateFootballTournamentByWrongNameType(){
        //GIVEN WHEN
        MatchCommonException exception = assertThrows(MatchCommonException.class,
                () -> TeamFactoryProducer.getFactory("Socccccer"));

        //THEN
        assertEquals(exception.getMessage(), ErrorMessageUtil.CANNOT_CREATE_FACTORY);
    }
}
