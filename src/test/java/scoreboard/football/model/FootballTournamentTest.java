package scoreboard.football.model;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class FootballTournamentTest {

    @Test
    public void shouldThrowExceptionWhenNullComparatorIsPassedToConstructor(){
        //GIVEN WHEN
        Comparator<FootballMatch> comparator = null;

        //THEN
        assertThrows(NullPointerException.class, () -> new FootballTournament(comparator));
    }

    @Test
    public void shouldCreateTournamentWithComparator(){
        //GIVEN WHEN
        Comparator<FootballMatch> comparator = Comparator.comparing(FootballMatch::getScoreTotal);
        FootballTournament footballTournament = new FootballTournament(comparator);

        //THEN
        assertEquals(footballTournament.getMatchComparator(), comparator);
    }

    @Test
    public void shouldCreateTournamentWithDefaultComparator(){
        //GIVEN WHEN
        FootballTournament footballTournament = new FootballTournament();

        //THEN
        assertNotNull(footballTournament.getMatchComparator());
    }

//    @Test
//    public void shouldSortMatchesWithComparator(){
//        //GIVEN WHEN
//        Comparator<FootballMatch> comparator = Comparator.comparing(FootballMatch::getScoreTotal);
//        FootballTournament footballTournament = new FootballTournament(comparator);
//
//        //THEN
//    }
//
//    @Test
//    public void shouldSortMatchesWithDefaultComparator(){
//        //GIVEN WHEN
//        FootballTournament footballTournament = new FootballTournament();
//
//        //THEN
//    }
}
