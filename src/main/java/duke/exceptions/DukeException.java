package duke.exceptions;

/**
 * A custom exception class representing exceptions unique to Duke.
 */
public class DukeException extends Exception {
    public DukeError error;

    public DukeException(DukeError error) {
        this.error = error;
    }
}

