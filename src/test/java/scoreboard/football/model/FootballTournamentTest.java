package scoreboard.football.model;

import org.junit.Test;
import scoreboard.football.datagenerator.FootballComparatorDataGenerator;
import scoreboard.football.datagenerator.FootballMatchDataGenerator;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

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

    @Test
    public void shouldThrowExceptionWhenNullComparatorIsPassedToSetter(){
        //GIVEN WHEN
        Comparator<FootballMatch> comparator = null;
        FootballTournament footballTournament = new FootballTournament();

        //THEN
        assertThrows(NullPointerException.class, () -> footballTournament.setMatchComparator(comparator));
    }

    @Test
    public void shouldSetComparatorToTournament(){
        //GIVEN WHEN
        Comparator<FootballMatch> comparator = Comparator.comparing(FootballMatch::getScoreTotal);
        FootballTournament footballTournament = new FootballTournament();
        footballTournament.setMatchComparator(comparator);

        //THEN
        assertEquals(footballTournament.getMatchComparator(), comparator);
    }

    @Test
    public void shouldSortMatchesWithComparator(){
        //GIVEN
        Comparator<FootballMatch> comparator = FootballComparatorDataGenerator.getDefaultComparator();
        FootballTournament footballTournament = spy(new FootballTournament(comparator));
        List<FootballMatch> activeMatches = FootballMatchDataGenerator.getMatchesWithoutDate();
        List<FootballMatch> matchesWithoutSort = FootballMatchDataGenerator.getMatchesWithoutDate();

        IntStream.range(0, activeMatches.size())
                .forEach(matchIndex -> {
                    FootballMatch match = activeMatches.get(matchIndex);
                    match.getScore().setHomeScore(1);
                    match.setStartDate(new Date(System.currentTimeMillis() - matchIndex));
                });

        //WHEN
        when(footballTournament.getActiveMatches()).thenReturn(activeMatches);

        //THEN
        FootballMatch firstMatch = footballTournament.getSortedMatches().get(0);
        FootballMatch lastMatch = footballTournament.getSortedMatches().get(footballTournament.getSortedMatches().size() - 1);
        assertEquals(firstMatch, matchesWithoutSort.get(matchesWithoutSort.size() - 1));
        assertEquals(lastMatch, matchesWithoutSort.get(0));
    }
}
