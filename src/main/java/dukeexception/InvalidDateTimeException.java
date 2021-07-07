package dukeexception;

public class InvalidDateTimeException extends DukeException {

    /**
     * Return message of the Thrown InvalidDateTimeException.
     *
     * @return String to be printed.
     */
    @Override
    public String getMessage() {
        return "ERROR: Date/time format is wrong!"
                + "\nTRY: YYYY-MM-DD HHmm";
    }
}
