package duke.exception;

/**
 * InvalidCommandException
 *
 * CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 29 Jan 2020
 *
 * @author Jel
 */
public class InvalidCommandException extends DukeException {
    /**
     * Overrides the Object's toString method
     * and contains details of the error.
     * @return The String that containing reason for the error.
     */
    @Override
    public String toString() {
        return "The command entered is invalid. Please try again.";
    }
}
