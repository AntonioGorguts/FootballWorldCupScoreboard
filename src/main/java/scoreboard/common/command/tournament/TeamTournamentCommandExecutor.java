package scoreboard.common.command.tournament;

import org.apache.log4j.Logger;
import scoreboard.football.processor.FootballTeamTournamentProcessor;

import java.util.Date;

public class TeamTournamentCommandExecutor {

    private static final Logger LOGGER = Logger.getLogger(TeamTournamentCommandExecutor.class);

    public void executeOperation(TeamTournamentCommand teamTournamentCommand){
        LOGGER.info(String.format("Starting executing command %s at %s", teamTournamentCommand.toString(), new Date()));
        teamTournamentCommand.execute();
    }
}
