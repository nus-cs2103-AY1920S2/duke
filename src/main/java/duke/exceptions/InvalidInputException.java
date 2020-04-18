package duke.exceptions;

/**
 * A Duke exception that warns users when entering invalid commands.
 */
public class InvalidInputException extends DukeException {

    /**
     * A string representation of the exception.
     * @return A specific message to warn the user.
     */
    public String toString() {
        return "OOPS! I'm sorry, but I don't know what that means :/";
    }
}