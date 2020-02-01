package duke.exceptions;

/**
 * Custom Abstract Exception to be used for the Duke application
 */
public abstract class DukeException extends Exception {
    /**
     * Returns a String representation of the exception
     * @return A String representation of the exception
     */
    @Override
    public abstract String toString();
}
