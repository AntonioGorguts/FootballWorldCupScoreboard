package scoreboard.common.factory;

import org.junit.Test;
import scoreboard.common.factory.producer.FactoryProducer;
import scoreboard.common.model.tournament.Tournament;
import scoreboard.common.model.type.MatchType;
import scoreboard.common.processor.TeamTournamentProcessor;
import scoreboard.exception.MatchCommonException;
import scoreboard.football.factory.FootballFactory;
import scoreboard.football.model.FootballTournament;
import scoreboard.football.processor.FootballTeamTournamentProcessor;
import scoreboard.util.ErrorMessageUtil;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class FactoryProducerTest {
    @Test
    public void shouldCreateFootballTournamentByType(){
        //GIVEN WHEN
        AbstractTeamSportFactory factory = FactoryProducer.getFactory(MatchType.FOOTBALL);

        //THEN
        assertThat(factory, instanceOf(FootballFactory.class));
    }

    @Test
    public void shouldCreateFootballTournamentByTypeName(){
        //GIVEN WHEN
        AbstractTeamSportFactory factory = FactoryProducer.getFactory("Football");

        //THEN
        assertThat(factory, instanceOf(FootballFactory.class));
    }

    @Test
    public void shouldCreateFootballTournamentByTypeNameIgnoresCase(){
        //GIVEN WHEN
        AbstractTeamSportFactory factory = FactoryProducer.getFactory("fOOtball");

        //THEN
        assertThat(factory, instanceOf(FootballFactory.class));
    }

    @Test
    public void shouldNotCreateFootballTournamentByNullType(){
        //GIVEN
        MatchType matchType = null;

        //WHEN
        MatchCommonException exception = assertThrows(MatchCommonException.class,
                () -> FactoryProducer.getFactory(matchType));

        //THEN
        assertEquals(exception.getMessage(), ErrorMessageUtil.CANNOT_CREATE_FACTORY);
    }

    @Test
    public void shouldNotCreateFootballTournamentByWrongNameType(){
        //GIVEN WHEN
        MatchCommonException exception = assertThrows(MatchCommonException.class,
                () -> FactoryProducer.getFactory("Socccccer"));

        //THEN
        assertEquals(exception.getMessage(), ErrorMessageUtil.CANNOT_CREATE_FACTORY);
    }
}
