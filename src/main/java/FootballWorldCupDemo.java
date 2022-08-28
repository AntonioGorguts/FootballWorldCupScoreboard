import scoreboard.football.command.FootballTournamentCommandExecutor;
import scoreboard.football.command.match.FootballMatchEndCommand;
import scoreboard.football.command.match.FootballMatchStartCommand;
import scoreboard.football.command.match.FootballMatchUpdateScoreCommand;
import scoreboard.football.command.scoreboard.FootballScoreboardPrintCommand;
import scoreboard.football.factory.FootballFactory;
import scoreboard.football.model.FootballMatch;
import scoreboard.football.model.FootballScore;
import scoreboard.football.model.FootballTeam;
import scoreboard.football.model.FootballTournament;
import scoreboard.football.processor.FootballTournamentProcessor;
import scoreboard.request.FootballMatchEndRequestDto;
import scoreboard.request.FootballMatchStartRequestDto;
import scoreboard.request.FootballMatchUpdateScoreRequestDto;

import java.util.ArrayList;
import java.util.List;

public class FootballWorldCupDemo {

    public static void main(String[] args) {
        //        Creating basic objects
        FootballTournament footballTournament = new FootballTournament();
        FootballTournamentProcessor footballTournamentProcessor = new FootballTournamentProcessor(footballTournament);
        FootballFactory footballFactory = new FootballFactory();

        FootballTournamentCommandExecutor footballTournamentCommandExecutor = new FootballTournamentCommandExecutor();
        FootballScoreboardPrintCommand footballScoreboardPrintCommand
                = new FootballScoreboardPrintCommand(footballTournamentProcessor);

        //        Creating demo data to start matches
        FootballMatchStartRequestDto footballMatchStartRequestDto1
                = new FootballMatchStartRequestDto("Mexico", "Canada");
        FootballMatchStartRequestDto footballMatchStartRequestDto2
                = new FootballMatchStartRequestDto("Spain", "Brazil");
        FootballMatchStartRequestDto footballMatchStartRequestDto3
                = new FootballMatchStartRequestDto("Germany", "France");
        FootballMatchStartRequestDto footballMatchStartRequestDto4
                = new FootballMatchStartRequestDto("Uruguay", "Italy");
        FootballMatchStartRequestDto footballMatchStartRequestDto5
                = new FootballMatchStartRequestDto("Argentina", "Australia");
        List<FootballMatchStartRequestDto> footballMatchStartRequestDtos = new ArrayList<>();
        footballMatchStartRequestDtos.add(footballMatchStartRequestDto5);
        footballMatchStartRequestDtos.add(footballMatchStartRequestDto4);
        footballMatchStartRequestDtos.add(footballMatchStartRequestDto3);
        footballMatchStartRequestDtos.add(footballMatchStartRequestDto2);
        footballMatchStartRequestDtos.add(footballMatchStartRequestDto1);

        //        Starting matches
        for (FootballMatchStartRequestDto footballMatchStartRequestDto : footballMatchStartRequestDtos) {
            FootballTeam homeTeam = footballFactory.createTeam(footballMatchStartRequestDto.getHomeTeam());
            FootballTeam awayTeam = footballFactory.createTeam(footballMatchStartRequestDto.getAwayTeam());
            FootballMatch footballMatch = footballFactory.createMatch(homeTeam, awayTeam);
            FootballMatchStartCommand matchStartCommand
                    = new FootballMatchStartCommand(footballTournamentProcessor, footballMatch);
            footballTournamentCommandExecutor.executeOperation(matchStartCommand);
        }

        footballTournamentCommandExecutor.executeOperation(footballScoreboardPrintCommand);
        System.out.println();

        //        Creating demo data to update matches
        FootballMatchUpdateScoreRequestDto matchUpdateScoreRequestDto1
                = new FootballMatchUpdateScoreRequestDto("Mexico", "Canada", 0, 5);
        FootballMatchUpdateScoreRequestDto matchUpdateScoreRequestDto2
                = new FootballMatchUpdateScoreRequestDto("Spain", "Brazil", 10, 2);
        FootballMatchUpdateScoreRequestDto matchUpdateScoreRequestDto3
                = new FootballMatchUpdateScoreRequestDto("Germany", "France", 2, 2);
        FootballMatchUpdateScoreRequestDto matchUpdateScoreRequestDto4
                = new FootballMatchUpdateScoreRequestDto("Uruguay", "Italy", 6, 6);
        FootballMatchUpdateScoreRequestDto matchUpdateScoreRequestDto5
                = new FootballMatchUpdateScoreRequestDto("Argentina", "Australia", 3, 1);
        List<FootballMatchUpdateScoreRequestDto> footballMatchUpdateRequestDtos = new ArrayList<>();
        footballMatchUpdateRequestDtos.add(matchUpdateScoreRequestDto1);
        footballMatchUpdateRequestDtos.add(matchUpdateScoreRequestDto2);
        footballMatchUpdateRequestDtos.add(matchUpdateScoreRequestDto3);
        footballMatchUpdateRequestDtos.add(matchUpdateScoreRequestDto4);
        footballMatchUpdateRequestDtos.add(matchUpdateScoreRequestDto5);

        //        Updating matches
        for (FootballMatchUpdateScoreRequestDto matchUpdateScoreRequestDto : footballMatchUpdateRequestDtos) {
            FootballTeam homeTeam = footballFactory.createTeam(matchUpdateScoreRequestDto.getHomeTeam());
            FootballTeam awayTeam = footballFactory.createTeam(matchUpdateScoreRequestDto.getAwayTeam());
            FootballMatch footballMatch = footballFactory.createMatch(homeTeam, awayTeam);
            FootballScore footballScore
                    = new FootballScore(matchUpdateScoreRequestDto.getHomeScore(), matchUpdateScoreRequestDto.getAwayScore());
            FootballMatchUpdateScoreCommand matchUpdateScoreCommand
                    = new FootballMatchUpdateScoreCommand(footballTournamentProcessor, footballMatch, footballScore);
            footballTournamentCommandExecutor.executeOperation(matchUpdateScoreCommand);
        }

        footballTournamentCommandExecutor.executeOperation(footballScoreboardPrintCommand);
        System.out.println();

        //        Creating demo data to end matches
        FootballMatchEndRequestDto matchEndRequestDto1 = new FootballMatchEndRequestDto("Mexico", "Canada");
        FootballMatchEndRequestDto matchEndRequestDto2 = new FootballMatchEndRequestDto("Spain", "Brazil");
        FootballMatchEndRequestDto matchEndRequestDto3 = new FootballMatchEndRequestDto("Germany", "France");
        FootballMatchEndRequestDto matchEndRequestDto4 = new FootballMatchEndRequestDto("Uruguay", "Italy");
        FootballMatchEndRequestDto matchEndRequestDto5 = new FootballMatchEndRequestDto("Argentina", "Australia");
        List<FootballMatchEndRequestDto> footballMatchEndRequestDtos = new ArrayList<>();
        footballMatchEndRequestDtos.add(matchEndRequestDto1);
        footballMatchEndRequestDtos.add(matchEndRequestDto3);
        footballMatchEndRequestDtos.add(matchEndRequestDto5);

        //        Ending some matches
        for (FootballMatchEndRequestDto footballMatchEndRequestDto : footballMatchEndRequestDtos) {
            FootballTeam homeTeam = footballFactory.createTeam(footballMatchEndRequestDto.getHomeTeam());
            FootballTeam awayTeam = footballFactory.createTeam(footballMatchEndRequestDto.getAwayTeam());
            FootballMatch footballMatch = footballFactory.createMatch(homeTeam, awayTeam);
            FootballMatchEndCommand matchEndCommand = new FootballMatchEndCommand(footballTournamentProcessor, footballMatch);
            footballTournamentCommandExecutor.executeOperation(matchEndCommand);
        }

        footballTournamentCommandExecutor.executeOperation(footballScoreboardPrintCommand);
        System.out.println();

        footballMatchEndRequestDtos.clear();
        footballMatchEndRequestDtos.add(matchEndRequestDto2);
        footballMatchEndRequestDtos.add(matchEndRequestDto4);

        //        Ending the rest of matches in the list
        for (FootballMatchEndRequestDto footballMatchEndRequestDto : footballMatchEndRequestDtos) {
            FootballTeam homeTeam = footballFactory.createTeam(footballMatchEndRequestDto.getHomeTeam());
            FootballTeam awayTeam = footballFactory.createTeam(footballMatchEndRequestDto.getAwayTeam());
            FootballMatch footballMatch = footballFactory.createMatch(homeTeam, awayTeam);
            FootballMatchEndCommand matchEndCommand = new FootballMatchEndCommand(footballTournamentProcessor, footballMatch);
            footballTournamentCommandExecutor.executeOperation(matchEndCommand);
        }

        footballTournamentCommandExecutor.executeOperation(footballScoreboardPrintCommand);
    }
}
