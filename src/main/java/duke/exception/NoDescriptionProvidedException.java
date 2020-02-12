package duke.exception;

/**
 * Represents a No Description Provided exception.
 *
 * <p>CS2103T AY19/20 Semester 2
 * Individual Duke project
 * 29 Jan 2020
 *
 * @author Jel
 */
public class NoDescriptionProvidedException extends DukeException {
    /**
     * Overrides the Object's toString method
     * and contains details of the error.
     * @return The String containing reason for the error.
     */
    @Override
    public String toString() {
        return "A description of the task has to be provided. Please try again.";
    }
}
