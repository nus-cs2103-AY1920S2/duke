package exceptions;

public class EventException extends DukeException {

    /**
     * Constructor for EventException
     *
     * @param message any message to convey
     */
    public EventException(String message) {
        super(message);
    }

    public String toString() {
        return ExceptionsConstant.FORMAT_LINE
                + "\nEvent tasks should be named/specified with time duration, Blur! XD\n"
                + "The format for the time/date should be 'YYYY-MM-DD' or 'YYYY/MM/DD'\n"
                + "Please try again!\n"
                + ExceptionsConstant.FORMAT_LINE;
    }

}
