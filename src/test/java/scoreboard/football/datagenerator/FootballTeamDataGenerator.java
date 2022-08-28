package scoreboard.football.datagenerator;

import scoreboard.football.model.FootballTeam;

import java.util.HashSet;
import java.util.Set;

public class FootballTeamDataGenerator {

    public static Set<FootballTeam> getActiveFootballTeams(){
        Set<FootballTeam> footballTeams = new HashSet<>();
        footballTeams.add(new FootballTeam("Mexico"));
        footballTeams.add(new FootballTeam("Canada"));
        footballTeams.add(new FootballTeam("Spain"));
        footballTeams.add(new FootballTeam("Brazil"));
        footballTeams.add(new FootballTeam("Germany"));
        footballTeams.add(new FootballTeam("France"));
        footballTeams.add(new FootballTeam("Uruguay"));
        footballTeams.add(new FootballTeam("Italy"));
        footballTeams.add(new FootballTeam("Argentina"));
        footballTeams.add(new FootballTeam("Australia"));
        return footballTeams;
    }
}
