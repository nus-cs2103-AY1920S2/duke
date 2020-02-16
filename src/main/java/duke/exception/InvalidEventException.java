package duke.exception;

/**
 * Thrown when user inputs invalid Event command.
 */
public class InvalidEventException extends InvalidFormatException {
    public InvalidEventException() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "\n\n" + "For event commands, follow this format: event {event name} "
                + "/at dd-mm-yyyy hh:mm";
    }
}
