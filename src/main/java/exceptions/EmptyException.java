package exceptions;

/**
 * Handles the exception of the description of the task is not given.
 */

public class EmptyException extends Exception {
    private static final String SPACE = "     ";
    public EmptyException() {};

    public String toString() {
        return SPACE + " ☹ OOPS!!! The description of a Task cannot be empty.";
    }
}
