package dukeexception;

public class LoadException extends DukeException {

    /**
     * Return message of the Thrown LoadException.
     *
     * @return String to be printed.
     */
    @Override
    public String getMessage() {
        return "ERROR: Not able to load file.";
    }
}
