package duke.exception;

/**
 * InvalidDateException
 *
 * CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 29 Jan 2020
 *
 * @author Jel
 */
public class InvalidDateException extends DukeException {
    /**
     * Overrides the Object's toString method
     * and contains details of the error.
     * @return The String that containing reason for the error.
     */
    @Override
    public String toString() {
        return "The date provided is invalid. Please provide a date of the format YYYY-MM-DD.";
    }
}