package duke.exceptions;

/**
 * Throws DateSearchFormatException when user does not specify
 * a correct date format while search for tasks with that specific date.
 *
 * @author ChesterSim
 */
public class DateSearchFormatException extends Exception {

    public DateSearchFormatException() {
        super();
    }

    @Override
    public String toString() {
        return "You have entered an invalid time/date format.\n"
                    + "Please follow the following format: 23:59 28/02/2020\n"
                    + "(padded with zero if necessary).";
    }
}
