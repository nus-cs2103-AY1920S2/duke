package duke.exceptions;

/**
 * Exception in thrown when user input is not recognized or of a wrong format
 */
public abstract class DukeException extends Exception {

    @Override
    public abstract String toString();
}
