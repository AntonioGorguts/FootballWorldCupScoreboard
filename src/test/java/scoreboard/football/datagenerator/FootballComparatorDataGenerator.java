package scoreboard.football.datagenerator;

import scoreboard.football.model.FootballMatch;

import java.util.Comparator;

public class FootballComparatorDataGenerator {

    public static Comparator<FootballMatch> getDefaultComparator(){
        return Comparator.comparing(FootballMatch::getScoreTotal).reversed()
                .thenComparing(FootballMatch::getStartDate);
    }
}
