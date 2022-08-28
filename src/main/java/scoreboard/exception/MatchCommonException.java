package scoreboard.exception;

//Added custom exception to provide some custom exception handlers in the future
public class MatchCommonException extends RuntimeException {
    public MatchCommonException(String message) {
        super(message);
    }

}
