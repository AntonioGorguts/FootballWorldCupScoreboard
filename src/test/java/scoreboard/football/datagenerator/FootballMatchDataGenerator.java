package scoreboard.football.datagenerator;

import scoreboard.football.model.FootballMatch;
import scoreboard.football.model.FootballTeam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FootballMatchDataGenerator {

    public static FootballMatch getPresentFootballMatch(){
        FootballTeam homeTeam = new FootballTeam("Mexico");
        FootballTeam awayTeam = new FootballTeam("Canada");
        FootballMatch footballMatch = new FootballMatch(homeTeam, awayTeam);
        return footballMatch;
    }

    public static FootballMatch getAbsentFootballMatch(){
        FootballTeam homeTeam = new FootballTeam("Ukraine");
        FootballTeam awayTeam = new FootballTeam("Poland");
        FootballMatch footballMatch = new FootballMatch(homeTeam, awayTeam);
        return footballMatch;
    }

    public static List<FootballMatch> getActiveMatches(){
        List<FootballMatch> footballMatches = new ArrayList<>();
        footballMatches.add(new FootballMatch(new FootballTeam("Argentina"), new FootballTeam("Australia")));
        footballMatches.add(new FootballMatch(new FootballTeam("Uruguay"), new FootballTeam("Italy")));
        footballMatches.add(new FootballMatch(new FootballTeam("Germany"), new FootballTeam("France")));
        footballMatches.add(new FootballMatch(new FootballTeam("Spain"), new FootballTeam("Brazil")));
        footballMatches.add(new FootballMatch(new FootballTeam("Mexico"), new FootballTeam("Canada")));
        for (FootballMatch match: footballMatches){
            match.setStartDate(new Date());
        }
        return footballMatches;
    }

    public static List<FootballMatch> getMatchesWithoutDate(){
        List<FootballMatch> footballMatches = new ArrayList<>();
        footballMatches.add(new FootballMatch(new FootballTeam("Argentina"), new FootballTeam("Australia")));
        footballMatches.add(new FootballMatch(new FootballTeam("Uruguay"), new FootballTeam("Italy")));
        footballMatches.add(new FootballMatch(new FootballTeam("Germany"), new FootballTeam("France")));
        footballMatches.add(new FootballMatch(new FootballTeam("Spain"), new FootballTeam("Brazil")));
        footballMatches.add(new FootballMatch(new FootballTeam("Mexico"), new FootballTeam("Canada")));
        return footballMatches;
    }
}
