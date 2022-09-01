package scoreboard.football.processor;

import datagenerator.FootballMatchRequestDtoDataGenerator;
import demo.request.FootballMatchEndRequestDto;
import demo.request.FootballMatchStartRequestDto;
import demo.request.FootballMatchUpdateScoreRequestDto;
import scoreboard.common.command.match.TeamMatchEndCommand;
import scoreboard.common.command.match.TeamMatchUpdateScoreCommand;
import scoreboard.common.factory.producer.TeamFactoryProducer;
import org.junit.Test;
import scoreboard.common.command.match.TeamMatchStartCommand;
import scoreboard.common.command.scoreboard.TeamScoreboardExportCommand;
import scoreboard.common.factory.AbstractTeamSportFactory;
import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.model.score.TeamScore;
import scoreboard.common.model.team.Team;
import scoreboard.common.model.tournament.TeamTournament;
import scoreboard.common.model.type.TeamMatchType;
import scoreboard.common.command.tournament.TeamTournamentCommandExecutor;
import scoreboard.common.processor.TeamTournamentProcessor;
import scoreboard.football.factory.FootballFactory;
import scoreboard.football.model.FootballMatch;
import scoreboard.football.model.FootballTournament;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FootballTournamentProcessorIT {

    @Test
    public void shouldCreateFootballTournamentByType(){
        //GIVEN WHEN
        AbstractTeamSportFactory factory = TeamFactoryProducer.getFactory(TeamMatchType.FOOTBALL);
        TeamTournament tournament = factory.createTournament(null);
        TeamTournamentProcessor tournamentProcessor = factory.createTournamentProcessor(tournament);

        //THEN
        assertThat(factory, instanceOf(FootballFactory.class));
        assertThat(tournament, instanceOf(FootballTournament.class));
        assertThat(tournamentProcessor, instanceOf(FootballTeamTournamentProcessor.class));
    }

    @Test
    public void shouldCreateFootballTournamentByTypeName(){
        //GIVEN WHEN
        AbstractTeamSportFactory factory = TeamFactoryProducer.getFactory("Football");
        TeamTournament tournament = factory.createTournament(null);
        TeamTournamentProcessor tournamentProcessor = factory.createTournamentProcessor(tournament);

        //THEN
        assertThat(factory, instanceOf(FootballFactory.class));
        assertThat(tournament, instanceOf(FootballTournament.class));
        assertThat(tournamentProcessor, instanceOf(FootballTeamTournamentProcessor.class));
    }

    @Test
    public void shouldStartFootballMatchesThroughCommand(){
        //GIVEN
        AbstractTeamSportFactory factory = TeamFactoryProducer.getFactory(TeamMatchType.FOOTBALL);
        TeamTournament tournament = factory.createTournament(null);
        TeamTournamentProcessor tournamentProcessor = factory.createTournamentProcessor(tournament);
        List<FootballMatchStartRequestDto> footballMatchStartRequestDtos = FootballMatchRequestDtoDataGenerator.getMatchStartRequestDtos();
        TeamTournamentCommandExecutor teamTournamentCommandExecutor = new TeamTournamentCommandExecutor();

        //WHEN THEN
        int timerIndex = footballMatchStartRequestDtos.size();
        assertEquals(tournament.getActiveMatches().size(), 0);
        for (FootballMatchStartRequestDto footballMatchStartRequestDto : footballMatchStartRequestDtos) {
            Team homeTeam = factory.createTeam(footballMatchStartRequestDto.getHomeTeam());
            Team awayTeam = factory.createTeam(footballMatchStartRequestDto.getAwayTeam());
            TeamMatch footballMatch = factory.createMatch(homeTeam, awayTeam);

            int testMillis = timerIndex * 1000;
            footballMatch.setStartDate(new Date(System.currentTimeMillis() - testMillis));
            TeamMatchStartCommand matchStartCommand
                    = new TeamMatchStartCommand(tournamentProcessor, footballMatch);
            teamTournamentCommandExecutor.executeOperation(matchStartCommand);
            timerIndex--;
        }

        boolean isFootballMatch = true;
        for (Object match : tournament.getActiveMatches()) {
            if (!match.getClass().equals(FootballMatch.class)){
                isFootballMatch = false;
                break;
            }
        }
        assertEquals(tournament.getActiveMatches().size(), footballMatchStartRequestDtos.size());
        assertTrue(isFootballMatch);
    }

    @Test
    public void shouldEndFootballMatchesThroughCommand(){
        //GIVEN
        AbstractTeamSportFactory factory = TeamFactoryProducer.getFactory(TeamMatchType.FOOTBALL);
        TeamTournament tournament = factory.createTournament(null);
        TeamTournamentProcessor tournamentProcessor = factory.createTournamentProcessor(tournament);
        List<FootballMatchStartRequestDto> footballMatchStartRequestDtos = FootballMatchRequestDtoDataGenerator.getMatchStartRequestDtos();
        List<FootballMatchEndRequestDto> footballMatchEndRequestDtos = FootballMatchRequestDtoDataGenerator.getMatchEndRequestDtos();
        TeamTournamentCommandExecutor teamTournamentCommandExecutor = new TeamTournamentCommandExecutor();

        //WHEN THEN
        int timerIndex = footballMatchStartRequestDtos.size();

        assertEquals(tournament.getActiveMatches().size(), 0);
        for (FootballMatchStartRequestDto footballMatchStartRequestDto : footballMatchStartRequestDtos) {
            Team homeTeam = factory.createTeam(footballMatchStartRequestDto.getHomeTeam());
            Team awayTeam = factory.createTeam(footballMatchStartRequestDto.getAwayTeam());
            TeamMatch footballMatch = factory.createMatch(homeTeam, awayTeam);
            int testMillis = timerIndex * 1000;
            footballMatch.setStartDate(new Date(System.currentTimeMillis() - testMillis));
            TeamMatchStartCommand matchStartCommand
                    = new TeamMatchStartCommand(tournamentProcessor, footballMatch);
            teamTournamentCommandExecutor.executeOperation(matchStartCommand);
            timerIndex--;
        }

        assertEquals(tournament.getActiveMatches().size(), footballMatchStartRequestDtos.size());
        for (FootballMatchEndRequestDto footballMatchEndRequestDto : footballMatchEndRequestDtos) {
            Team homeTeam = factory.createTeam(footballMatchEndRequestDto.getHomeTeam());
            Team awayTeam = factory.createTeam(footballMatchEndRequestDto.getAwayTeam());
            TeamMatch footballMatch = factory.createMatch(homeTeam, awayTeam);
            TeamMatchEndCommand matchEndCommand = new TeamMatchEndCommand(tournamentProcessor, footballMatch);
            teamTournamentCommandExecutor.executeOperation(matchEndCommand);
        }

        assertEquals(tournament.getActiveMatches().size(), 0);
    }

    @Test
    public void shouldUpdateFootballMatchesScoreThroughCommand() {
        //GIVEN
        AbstractTeamSportFactory factory = TeamFactoryProducer.getFactory(TeamMatchType.FOOTBALL);
        TeamTournament tournament = factory.createTournament(null);
        TeamTournamentProcessor tournamentProcessor = factory.createTournamentProcessor(tournament);
        List<FootballMatchStartRequestDto> footballMatchStartRequestDtos = FootballMatchRequestDtoDataGenerator.getMatchStartRequestDtos();
        List<FootballMatchUpdateScoreRequestDto> matchUpdateScoreRequestDtos = FootballMatchRequestDtoDataGenerator.getMatchUpdateScoreRequestDtos();
        TeamTournamentCommandExecutor teamTournamentCommandExecutor = new TeamTournamentCommandExecutor();

        //WHEN THEN
        int timerIndex = footballMatchStartRequestDtos.size();

        assertEquals(tournament.getActiveMatches().size(), 0);
        for (FootballMatchStartRequestDto footballMatchStartRequestDto : footballMatchStartRequestDtos) {
            Team homeTeam = factory.createTeam(footballMatchStartRequestDto.getHomeTeam());
            Team awayTeam = factory.createTeam(footballMatchStartRequestDto.getAwayTeam());
            TeamMatch footballMatch = factory.createMatch(homeTeam, awayTeam);
            int testMillis = timerIndex * 1000;
            footballMatch.setStartDate(new Date(System.currentTimeMillis() - testMillis));
            TeamMatchStartCommand matchStartCommand
                    = new TeamMatchStartCommand(tournamentProcessor, footballMatch);
            teamTournamentCommandExecutor.executeOperation(matchStartCommand);
            timerIndex--;
        }

        assertEquals(tournament.getActiveMatches().size(), footballMatchStartRequestDtos.size());

        for (FootballMatchUpdateScoreRequestDto matchUpdateScoreRequestDto : matchUpdateScoreRequestDtos) {
            Team homeTeam = factory.createTeam(matchUpdateScoreRequestDto.getHomeTeam());
            Team awayTeam = factory.createTeam(matchUpdateScoreRequestDto.getAwayTeam());
            TeamMatch footballMatch = factory.createMatch(homeTeam, awayTeam);
            TeamScore footballScore
                    = factory.createScore(matchUpdateScoreRequestDto.getHomeScore(), matchUpdateScoreRequestDto.getAwayScore());
            TeamMatchUpdateScoreCommand matchUpdateScoreCommand
                    = new TeamMatchUpdateScoreCommand(tournamentProcessor, footballMatch, footballScore);
            teamTournamentCommandExecutor.executeOperation(matchUpdateScoreCommand);
        }

        boolean isUpdated = true;
        for (Object match : tournament.getActiveMatches()) {
            FootballMatch footballMatch = (FootballMatch) match;
            Optional<FootballMatchUpdateScoreRequestDto> matchUpdateScoreRequestDto
                    = matchUpdateScoreRequestDtos.stream().filter(matchUpdate
                    -> matchUpdate.getHomeScore() == footballMatch.getScore().getHomeScore()
                    || matchUpdate.getAwayScore() == footballMatch.getScore().getAwayScore()
                            || matchUpdate.getHomeTeam().equals(footballMatch.getHomeTeam().getName())
                            || matchUpdate.getAwayTeam().equals(footballMatch.getAwayTeam().getName()))
                    .findAny();
            if(!matchUpdateScoreRequestDto.isPresent()){
                isUpdated = false;
                break;
            }
        }
       assertTrue(isUpdated);
    }

    @Test
    public void shouldExportFootballMatchesScoreboardThroughCommand(){
        //GIVEN
        AbstractTeamSportFactory factory = TeamFactoryProducer.getFactory(TeamMatchType.FOOTBALL);
        TeamTournament tournament = factory.createTournament(null);
        TeamTournamentProcessor tournamentProcessor = factory.createTournamentProcessor(tournament);
        List<FootballMatchStartRequestDto> footballMatchStartRequestDtos = FootballMatchRequestDtoDataGenerator.getMatchStartRequestDtos();
        TeamTournamentCommandExecutor teamTournamentCommandExecutor = new TeamTournamentCommandExecutor();
        TeamScoreboardExportCommand scoreboardExportCommand = new TeamScoreboardExportCommand(tournamentProcessor);
        int timerIndex = footballMatchStartRequestDtos.size();

        //WHEN THEN
        assertEquals(scoreboardExportCommand.getScoreboard().size(), 0);
        for (FootballMatchStartRequestDto footballMatchStartRequestDto : footballMatchStartRequestDtos) {
            Team homeTeam = factory.createTeam(footballMatchStartRequestDto.getHomeTeam());
            Team awayTeam = factory.createTeam(footballMatchStartRequestDto.getAwayTeam());
            TeamMatch footballMatch = factory.createMatch(homeTeam, awayTeam);
            int testMillis = timerIndex * 1000;
            footballMatch.setStartDate(new Date(System.currentTimeMillis() - testMillis));
            TeamMatchStartCommand matchStartCommand
                    = new TeamMatchStartCommand(tournamentProcessor, footballMatch);
            teamTournamentCommandExecutor.executeOperation(matchStartCommand);
            timerIndex--;
        }
        teamTournamentCommandExecutor.executeOperation(scoreboardExportCommand);

        assertEquals(footballMatchStartRequestDtos.size(), scoreboardExportCommand.getScoreboard().size());
    }

}
