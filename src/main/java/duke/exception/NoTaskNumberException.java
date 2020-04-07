package duke.exception;

/**
 * Represents a No Task Number exception.
 *
 * <p>CS2103T AY19/20 Semester 2
 * Individual Duke project
 * 29 Jan 2020
 *
 * @author Jel
 */
public class NoTaskNumberException extends DukeException {
    /**
     * Overrides the Object's toString method
     * and contains details of the error.
     * @return The String containing reason for the error and how to resolve it.
     */
    @Override
    public String toString() {
        return "Task number of task to be marked or deleted has to be provided. Please try again.";
    }
}
