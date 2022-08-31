package scoreboard.common.factory;

import scoreboard.common.model.team.Team;

public interface TeamFactory {
    Team createTeam(String name);
}
