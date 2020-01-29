package duke.exceptions;

public class EmptyTaskListException extends DukeException {

    /**
     * Constructor for EmptyTaskListException
     *
     * @param message any message to convey
     */
    public EmptyTaskListException(String message) {
        super(message);
    }

    public String toString() {
        return ExceptionsConstant.FORMAT_LINE
                + "\nNo tasks! You're good to go!\nPlease exit using 'bye' command. :)\n"
                + ExceptionsConstant.FORMAT_LINE;
    }


}
