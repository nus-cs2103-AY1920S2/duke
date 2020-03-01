package duke.parser.exception;

import duke.DukeException;

/**
 * Represents a parse error encountered because of insufficient arguments.
 */
public class MissingParserArgumentsException extends ParseException {
    /**
     * Constructs a new {@code MissingParsedArgumentsException} when a
     * parse fails because of insufficient arguments.
     */
    public MissingParserArgumentsException() {
        super("You missed out some arguments, blockhead.");
    }
}
