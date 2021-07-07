package dukeexception;

public class InvalidIndexException extends DukeException {

    /**
     * Return message of the Thrown InvalidIndexException.
     *
     * @return String to be printed.
     */
    @Override
    public String getMessage() {
        return "ERROR: INVALID INDEX";
    }
}
