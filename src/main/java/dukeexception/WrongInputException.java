package dukeexception;

public class WrongInputException extends DukeException {

    /**
     * Return message of the Thrown WrongInputException.
     *
     * @return String to be printed.
     */
    @Override
    public String getMessage() {
        return "ERROR: Something is missing! Be specific!";
    }
}
