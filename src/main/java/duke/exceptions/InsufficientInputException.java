package duke.exceptions;

/**
 * A Duke Exception which warns the users that arguments are not enough.
 */
public class InsufficientInputException extends DukeException {

    public String toString() {
        return "OOPS, the arguments are not enough!";
    }
}