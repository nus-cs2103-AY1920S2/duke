package duke.exceptions;

/**
 * Exception when there are issues with Events.
 */
public class EventException extends DukeException {

    /**
     * Constructor for EventException.
     *
     * @param message any message to convey.
     */
    public EventException(String message) {
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
                + "\nEvent tasks should be named/specified with time duration, Blur! XD\n"
                + "The format for the time/date should be 'YYYY-MM-DD' or 'YYYY/MM/DD'\n"
                + "Please try again!\n"
                + ExceptionsConstant.FORMAT_LINE;
    }

}
