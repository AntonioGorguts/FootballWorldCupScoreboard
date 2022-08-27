package scoreboard.football.model;

import scoreboard.common.model.team.Team;

public class FootballTeam implements Team {

    private final String name;

    public FootballTeam(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return null;
    }
}
