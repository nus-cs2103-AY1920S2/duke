package duke;

/**
 * Custom exception to be thrown when there is an error parsing user input, or if task list fails to load or save.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
