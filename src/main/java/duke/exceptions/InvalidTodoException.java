package duke.exceptions;

/**
 * A Duke exception to warn the users when creating invalid tasks.
 */
public class InvalidTodoException extends DukeException {

    /**
     * A string representation of the exception as a message.
     * @return A specific message to warn the user about invalid task constructors.
     */
    public String toString() {
        return "OOPS! Description of Todo cannot be empty.";
    }

}