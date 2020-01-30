package duke.exception;

/**
 * NoDescriptionException
 *
 * CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 29 Jan 2020
 *
 * @author Jel
 */
public class NoDescriptionException extends DukeException {
    /**
     * Overrides the Object's toString method
     * and contains details of the error.
     * @return The String that containing reason for the error.
     */
    @Override
    public String toString() {
        return "A description of the task has to be provided. Please try again.";
    }
}
