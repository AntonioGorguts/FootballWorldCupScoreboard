package scoreboard.football.model;

import org.apache.commons.lang3.StringUtils;
import scoreboard.common.model.tournament.TeamTournament;
import scoreboard.common.model.tournament.Tournament;
import scoreboard.exception.MatchCommonException;
import scoreboard.util.ErrorMessageUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class FootballTournament implements TeamTournament<FootballTeam>, Tournament<FootballMatch> {

    private static final Comparator<FootballMatch> DEFAULT_COMPARATOR =
            Comparator.comparing(FootballMatch::getScoreTotal).reversed()
                    .thenComparing(FootballMatch::getStartDate);

    private final List<FootballMatch> activeMatches = new ArrayList<>();
    private final Set<FootballTeam> activeTeams = new HashSet<>();
    private Comparator<FootballMatch> matchComparator;

    public FootballTournament() {
        matchComparator = DEFAULT_COMPARATOR;
    }

    public FootballTournament(Comparator<FootballMatch> matchComparator) {
        this.matchComparator = Objects.requireNonNull(matchComparator);
    }

    public Comparator<FootballMatch> getMatchComparator() {
        return matchComparator;
    }

    public void setMatchComparator(Comparator<FootballMatch> matchComparator) {
        this.matchComparator = Objects.requireNonNull(matchComparator);
    }

    //To not allow to change actual list through getter
    @Override
    public List<FootballMatch> getActiveMatches() {
        return new ArrayList<>(activeMatches);
    }

    //To not allow to change actual set through getter
    @Override
    public Set<FootballTeam> getActiveTeams() {
        return new HashSet<>(activeTeams);
    }

    @Override
    public List<FootballMatch> getSortedMatches() {
        //to leave the original list in original order
        List<FootballMatch> matches = new ArrayList<>(getActiveMatches());
        matches.sort(matchComparator);
        return matches;
    }

    @Override
    public void addActiveTeam(FootballTeam team){
        if (activeTeams.contains(team)){
            throw new IllegalArgumentException(ErrorMessageUtil.TEAM_ALREADY_PLAYING);
        }
        activeTeams.add(team);
    }

    @Override
    public void removeActiveTeam(FootballTeam team){
        if (!activeTeams.contains(team)){
            throw new IllegalArgumentException(ErrorMessageUtil.TEAM_IS_ABSENT);
        }
        activeTeams.remove(team);
    }

    @Override
    public void addActiveMatch(FootballMatch match){
        matchCommonValidation(match);
        if(activeMatches.contains(match)) {
            throw new IllegalArgumentException(ErrorMessageUtil.MATCH_ALREADY_PLAYING);
        }
        activeMatches.add(match);
    }

    @Override
    public void removeActiveMatch(FootballMatch match){
        matchCommonValidation(match);
        if(!activeMatches.contains(match)) {
            throw new IllegalArgumentException(ErrorMessageUtil.MATCH_NOT_FOUND);
        }
        activeMatches.remove(match);
    }

    private void matchCommonValidation(FootballMatch match){
        if (match == null){
            throw new IllegalArgumentException(ErrorMessageUtil.MATCH_NOT_NULL);
        }
        if (match.getHomeTeam() == null || StringUtils.isBlank(match.getHomeTeam().getName())){
            throw new IllegalArgumentException(ErrorMessageUtil.HOME_TEAM_NOT_BLANK);
        }
        if (match.getAwayTeam() == null || StringUtils.isBlank(match.getAwayTeam().getName())){
            throw new IllegalArgumentException(ErrorMessageUtil.AWAY_TEAM_NOT_BLANK);
        }
        if (match.getHomeTeam().equals(match.getAwayTeam())) {
            throw new IllegalArgumentException(ErrorMessageUtil.SAME_HOME_AWAY_TEAM);
        }
    }
}
