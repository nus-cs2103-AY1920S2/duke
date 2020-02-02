package dukeexception;

public class SaveException extends DukeException {

    /**
     * Return message of the Thrown SaveException.
     *
     * @return String to be printed.
     */
    @Override
    public String getMessage() {
        return "ERROR: Unable to save file.";
    }

}
