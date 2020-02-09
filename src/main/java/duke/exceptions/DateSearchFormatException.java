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
        return "Sorry, I don't recognize this date format. Try to follow this format (DD/MM/YYYY): 28/02/2020";
    }
}
