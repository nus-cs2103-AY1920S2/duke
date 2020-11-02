package duke.exception;

/**
 * Thrown when user inputs invalid Deadline command.
 */
public class InvalidDeadlineException extends InvalidFormatException {
    public InvalidDeadlineException() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "\n\n" + "For deadline commands, follow this format: deadline {deadline name} "
                + "/by dd-mm-yyyy hh:mm";
    }
}
