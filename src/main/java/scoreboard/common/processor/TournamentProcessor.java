package scoreboard.common.processor;

public interface TournamentProcessor<Match> {
    void startMatch(Match match);
    void endMatch(Match match);
}
