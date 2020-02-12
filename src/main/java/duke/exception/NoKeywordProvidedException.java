package duke.exception;

/**
 * Represents a No Keyword Provided exception.
 *
 * <p>CS2103T AY19/20 Semester 2
 * Individual Duke project
 * 29 Jan 2020
 *
 * @author Jel
 */
public class NoKeywordProvidedException extends DukeException {
    /**
     * Overrides the Object's toString method
     * and contains details of the error.
     * @return The String containing reason for the error.
     */
    @Override
    public String toString() {
        return "Keyword has to be provided by finding certain tasks. Please try again.";
    }
}
