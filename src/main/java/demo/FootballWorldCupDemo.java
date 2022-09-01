package demo;

import scoreboard.common.command.match.TeamMatchEndCommandTeam;
import scoreboard.common.command.match.TeamMatchStartCommandTeam;
import scoreboard.common.command.match.TeamMatchUpdateScoreCommandTeam;
import scoreboard.common.command.scoreboard.TeamScoreboardPrintCommand;
import scoreboard.common.command.tournament.TeamTournamentCommandExecutor;
import scoreboard.football.factory.FootballFactory;
import scoreboard.football.model.FootballMatch;
import scoreboard.football.model.FootballScore;
import scoreboard.football.model.FootballTeam;
import scoreboard.football.model.FootballTournament;
import scoreboard.football.processor.FootballTeamTournamentProcessor;
import demo.request.FootballMatchEndRequestDto;
import demo.request.FootballMatchStartRequestDto;
import demo.request.FootballMatchUpdateScoreRequestDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FootballWorldCupDemo {

    public static void main(String[] args) {
        //        Creating basic objects

        // Utilize the match factory to create a new football match entity
        FootballFactory footballFactory = new FootballFactory();
        // Instantiate footballTournament, which contains active matches and teams and comparator to sort matches
        FootballTournament footballTournament = new FootballTournament();
        // Instantiate the footballTournamentProcessor, that will deal with tournament functionality
        FootballTeamTournamentProcessor footballTournamentProcessor = new FootballTeamTournamentProcessor(footballTournament);

        // Instantiate the footballTournamentCommandExecutor, that will trigger FootballTournamentCommand
        // to deal with footballTournamentProcessor functions
        TeamTournamentCommandExecutor teamTournamentCommandExecutor = new TeamTournamentCommandExecutor();

        // Instantiate the footballScoreboardPrintCommand, that will call print scoreboard method of footballTournamentProcessor
        // when executed
        TeamScoreboardPrintCommand footballScoreboardPrintCommand
                = new TeamScoreboardPrintCommand(footballTournamentProcessor);

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
        int timerIndex = footballMatchStartRequestDtos.size();
        for (FootballMatchStartRequestDto footballMatchStartRequestDto : footballMatchStartRequestDtos) {
            FootballTeam homeTeam = footballFactory.createTeam(footballMatchStartRequestDto.getHomeTeam());
            FootballTeam awayTeam = footballFactory.createTeam(footballMatchStartRequestDto.getAwayTeam());
            FootballMatch footballMatch = footballFactory.createMatch(homeTeam, awayTeam);
            int testMillis = timerIndex * 1000;
            footballMatch.setStartDate(new Date(System.currentTimeMillis() - testMillis));
            TeamMatchStartCommandTeam matchStartCommand
                    = new TeamMatchStartCommandTeam(footballTournamentProcessor, footballMatch);
            teamTournamentCommandExecutor.executeOperation(matchStartCommand);
            timerIndex--;
        }

        teamTournamentCommandExecutor.executeOperation(footballScoreboardPrintCommand);
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
            TeamMatchUpdateScoreCommandTeam matchUpdateScoreCommand
                    = new TeamMatchUpdateScoreCommandTeam(footballTournamentProcessor, footballMatch, footballScore);
            teamTournamentCommandExecutor.executeOperation(matchUpdateScoreCommand);
        }

        teamTournamentCommandExecutor.executeOperation(footballScoreboardPrintCommand);
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
            TeamMatchEndCommandTeam matchEndCommand = new TeamMatchEndCommandTeam(footballTournamentProcessor, footballMatch);
            teamTournamentCommandExecutor.executeOperation(matchEndCommand);
        }

        teamTournamentCommandExecutor.executeOperation(footballScoreboardPrintCommand);
        System.out.println();

        footballMatchEndRequestDtos.clear();
        footballMatchEndRequestDtos.add(matchEndRequestDto2);
        footballMatchEndRequestDtos.add(matchEndRequestDto4);

        //        Ending the rest of matches in the list
        for (FootballMatchEndRequestDto footballMatchEndRequestDto : footballMatchEndRequestDtos) {
            FootballTeam homeTeam = footballFactory.createTeam(footballMatchEndRequestDto.getHomeTeam());
            FootballTeam awayTeam = footballFactory.createTeam(footballMatchEndRequestDto.getAwayTeam());
            FootballMatch footballMatch = footballFactory.createMatch(homeTeam, awayTeam);
            TeamMatchEndCommandTeam matchEndCommand = new TeamMatchEndCommandTeam(footballTournamentProcessor, footballMatch);
            teamTournamentCommandExecutor.executeOperation(matchEndCommand);
        }

        teamTournamentCommandExecutor.executeOperation(footballScoreboardPrintCommand);
    }
}
