package duke.exception;

/**
 * Signals error when there is an I/O exception in storage.
 */
public class DukeIoException extends DukeException {

    @Override
    public String getMessage() {
        return "OOPS!!! There seems to be an I/O error!";
    }
}
