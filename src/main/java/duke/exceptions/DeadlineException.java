package duke.exceptions;

/**
 * Exception when deadline command has issue.
 */
public class DeadlineException extends DukeException {

    /**
     * Constructor for DeadlineException.
     *
     * @param message any message to convey.
     */
    public DeadlineException(String message) {
        super(message);
    }

    /**
     * Provides a custom String representation of the current Exception, which includes formatting lines.
     *
     * @return the presentable String representation.
     */
    @Override
    public String toString() {
        return ExceptionsConstant.FORMAT_LINE
                + "\nDeadline tasks should be named/specified with the deadline, Funny! XD\n"
                + "The format for the time/date should be 'YYYY-MM-DD' or 'YYYY/MM/DD'\n"
                + "Please try again!\n"
                + ExceptionsConstant.FORMAT_LINE;
    }

}
