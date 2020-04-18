package duke.exceptions;

/**
 * A Duke exception which warns about input and output errors.
 */
public class DukeIOException extends DukeException {

    public String toString() {
        return "OOPS! Something is wrong with the input/output.";
    }
}