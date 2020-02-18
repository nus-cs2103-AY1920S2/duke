package duke.exception;

public class MissingParsedArgumentsException extends DukeException {
    /**
     * Constructs a new exception when a parse fails because of insufficient arguments.
     */
    public MissingParsedArgumentsException() {
        super("You missed out some arguments, blockhead.");
    }
}