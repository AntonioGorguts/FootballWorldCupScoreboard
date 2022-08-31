package scoreboard.football.processor;

import datagenerator.FootballMatchRequestDtoDataGenerator;
import demo.request.FootballMatchEndRequestDto;
import demo.request.FootballMatchStartRequestDto;
import demo.request.FootballMatchUpdateScoreRequestDto;
import scoreboard.common.command.match.MatchEndCommand;
import scoreboard.common.command.match.MatchUpdateScoreCommand;
import scoreboard.common.factory.producer.FactoryProducer;
import org.junit.Test;
import scoreboard.common.command.match.MatchStartCommand;
import scoreboard.common.command.scoreboard.ScoreboardExportCommand;
import scoreboard.common.factory.AbstractTeamSportFactory;
import scoreboard.common.model.match.TeamMatch;
import scoreboard.common.model.score.TeamScore;
import scoreboard.common.model.team.Team;
import scoreboard.common.model.tournament.TeamTournament;
import scoreboard.common.model.type.MatchType;
import scoreboard.common.processor.TeamTournamentProcessor;
import scoreboard.common.command.tournament.TournamentCommandExecutor;
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
        AbstractTeamSportFactory factory = FactoryProducer.getFactory(MatchType.FOOTBALL);
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
        AbstractTeamSportFactory factory = FactoryProducer.getFactory("Football");
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
        AbstractTeamSportFactory factory = FactoryProducer.getFactory(MatchType.FOOTBALL);
        TeamTournament tournament = factory.createTournament(null);
        TeamTournamentProcessor tournamentProcessor = factory.createTournamentProcessor(tournament);
        List<FootballMatchStartRequestDto> footballMatchStartRequestDtos = FootballMatchRequestDtoDataGenerator.getMatchStartRequestDtos();
        TournamentCommandExecutor tournamentCommandExecutor = new TournamentCommandExecutor();

        //WHEN THEN
        int timerIndex = footballMatchStartRequestDtos.size();
        assertEquals(tournament.getActiveMatches().size(), 0);
        for (FootballMatchStartRequestDto footballMatchStartRequestDto : footballMatchStartRequestDtos) {
            Team homeTeam = factory.createTeam(footballMatchStartRequestDto.getHomeTeam());
            Team awayTeam = factory.createTeam(footballMatchStartRequestDto.getAwayTeam());
            TeamMatch footballMatch = factory.createMatch(homeTeam, awayTeam);

            int testMillis = timerIndex * 1000;
            footballMatch.setStartDate(new Date(System.currentTimeMillis() - testMillis));
            MatchStartCommand matchStartCommand
                    = new MatchStartCommand(tournamentProcessor, footballMatch);
            tournamentCommandExecutor.executeOperation(matchStartCommand);
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
        AbstractTeamSportFactory factory = FactoryProducer.getFactory(MatchType.FOOTBALL);
        TeamTournament tournament = factory.createTournament(null);
        TeamTournamentProcessor tournamentProcessor = factory.createTournamentProcessor(tournament);
        List<FootballMatchStartRequestDto> footballMatchStartRequestDtos = FootballMatchRequestDtoDataGenerator.getMatchStartRequestDtos();
        List<FootballMatchEndRequestDto> footballMatchEndRequestDtos = FootballMatchRequestDtoDataGenerator.getMatchEndRequestDtos();
        TournamentCommandExecutor tournamentCommandExecutor = new TournamentCommandExecutor();

        //WHEN THEN
        int timerIndex = footballMatchStartRequestDtos.size();

        assertEquals(tournament.getActiveMatches().size(), 0);
        for (FootballMatchStartRequestDto footballMatchStartRequestDto : footballMatchStartRequestDtos) {
            Team homeTeam = factory.createTeam(footballMatchStartRequestDto.getHomeTeam());
            Team awayTeam = factory.createTeam(footballMatchStartRequestDto.getAwayTeam());
            TeamMatch footballMatch = factory.createMatch(homeTeam, awayTeam);
            int testMillis = timerIndex * 1000;
            footballMatch.setStartDate(new Date(System.currentTimeMillis() - testMillis));
            MatchStartCommand matchStartCommand
                    = new MatchStartCommand(tournamentProcessor, footballMatch);
            tournamentCommandExecutor.executeOperation(matchStartCommand);
            timerIndex--;
        }

        assertEquals(tournament.getActiveMatches().size(), footballMatchStartRequestDtos.size());
        for (FootballMatchEndRequestDto footballMatchEndRequestDto : footballMatchEndRequestDtos) {
            Team homeTeam = factory.createTeam(footballMatchEndRequestDto.getHomeTeam());
            Team awayTeam = factory.createTeam(footballMatchEndRequestDto.getAwayTeam());
            TeamMatch footballMatch = factory.createMatch(homeTeam, awayTeam);
            MatchEndCommand matchEndCommand = new MatchEndCommand(tournamentProcessor, footballMatch);
            tournamentCommandExecutor.executeOperation(matchEndCommand);
        }

        assertEquals(tournament.getActiveMatches().size(), 0);
    }

    @Test
    public void shouldUpdateFootballMatchesScoreThroughCommand() {
        //GIVEN
        AbstractTeamSportFactory factory = FactoryProducer.getFactory(MatchType.FOOTBALL);
        TeamTournament tournament = factory.createTournament(null);
        TeamTournamentProcessor tournamentProcessor = factory.createTournamentProcessor(tournament);
        List<FootballMatchStartRequestDto> footballMatchStartRequestDtos = FootballMatchRequestDtoDataGenerator.getMatchStartRequestDtos();
        List<FootballMatchUpdateScoreRequestDto> matchUpdateScoreRequestDtos = FootballMatchRequestDtoDataGenerator.getMatchUpdateScoreRequestDtos();
        TournamentCommandExecutor tournamentCommandExecutor = new TournamentCommandExecutor();

        //WHEN THEN
        int timerIndex = footballMatchStartRequestDtos.size();

        assertEquals(tournament.getActiveMatches().size(), 0);
        for (FootballMatchStartRequestDto footballMatchStartRequestDto : footballMatchStartRequestDtos) {
            Team homeTeam = factory.createTeam(footballMatchStartRequestDto.getHomeTeam());
            Team awayTeam = factory.createTeam(footballMatchStartRequestDto.getAwayTeam());
            TeamMatch footballMatch = factory.createMatch(homeTeam, awayTeam);
            int testMillis = timerIndex * 1000;
            footballMatch.setStartDate(new Date(System.currentTimeMillis() - testMillis));
            MatchStartCommand matchStartCommand
                    = new MatchStartCommand(tournamentProcessor, footballMatch);
            tournamentCommandExecutor.executeOperation(matchStartCommand);
            timerIndex--;
        }

        assertEquals(tournament.getActiveMatches().size(), footballMatchStartRequestDtos.size());

        for (FootballMatchUpdateScoreRequestDto matchUpdateScoreRequestDto : matchUpdateScoreRequestDtos) {
            Team homeTeam = factory.createTeam(matchUpdateScoreRequestDto.getHomeTeam());
            Team awayTeam = factory.createTeam(matchUpdateScoreRequestDto.getAwayTeam());
            TeamMatch footballMatch = factory.createMatch(homeTeam, awayTeam);
            TeamScore footballScore
                    = factory.createScore(matchUpdateScoreRequestDto.getHomeScore(), matchUpdateScoreRequestDto.getAwayScore());
            MatchUpdateScoreCommand matchUpdateScoreCommand
                    = new MatchUpdateScoreCommand(tournamentProcessor, footballMatch, footballScore);
            tournamentCommandExecutor.executeOperation(matchUpdateScoreCommand);
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
        AbstractTeamSportFactory factory = FactoryProducer.getFactory(MatchType.FOOTBALL);
        TeamTournament tournament = factory.createTournament(null);
        TeamTournamentProcessor tournamentProcessor = factory.createTournamentProcessor(tournament);
        List<FootballMatchStartRequestDto> footballMatchStartRequestDtos = FootballMatchRequestDtoDataGenerator.getMatchStartRequestDtos();
        TournamentCommandExecutor tournamentCommandExecutor = new TournamentCommandExecutor();
        ScoreboardExportCommand scoreboardExportCommand = new ScoreboardExportCommand(tournamentProcessor);
        int timerIndex = footballMatchStartRequestDtos.size();

        //WHEN THEN
        assertEquals(scoreboardExportCommand.getScoreboard().size(), 0);
        for (FootballMatchStartRequestDto footballMatchStartRequestDto : footballMatchStartRequestDtos) {
            Team homeTeam = factory.createTeam(footballMatchStartRequestDto.getHomeTeam());
            Team awayTeam = factory.createTeam(footballMatchStartRequestDto.getAwayTeam());
            TeamMatch footballMatch = factory.createMatch(homeTeam, awayTeam);
            int testMillis = timerIndex * 1000;
            footballMatch.setStartDate(new Date(System.currentTimeMillis() - testMillis));
            MatchStartCommand matchStartCommand
                    = new MatchStartCommand(tournamentProcessor, footballMatch);
            tournamentCommandExecutor.executeOperation(matchStartCommand);
            timerIndex--;
        }
        tournamentCommandExecutor.executeOperation(scoreboardExportCommand);

        assertEquals(footballMatchStartRequestDtos.size(), scoreboardExportCommand.getScoreboard().size());
    }

}
