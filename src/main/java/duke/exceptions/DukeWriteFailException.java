package duke.exceptions;

/**
 * Handles event where modified data cannot be written back into data file.
 */
public class DukeWriteFailException extends DukeException {
    public DukeWriteFailException() {
        super.message = "For some reason, I was unable to write the changes back to the data file!! :O";
    }
}
