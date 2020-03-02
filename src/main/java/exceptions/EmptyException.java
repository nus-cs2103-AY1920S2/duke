package exceptions;

/**
 * Handles the exception of the description of the task is not given.
 */

public class EmptyException extends Exception {
    public EmptyException() {};

    public String toString() {
        return " ☹ OOPS!!! The description of a command cannot be empty.";
    }
}
