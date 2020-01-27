package duke;

/**
 * Signals any error sent by Duke.
 */
public class DukeException extends Exception {

    public DukeException() {}

    public DukeException(String message) {
        super(message);
    }
}
