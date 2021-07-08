package duke.exception;

/**
 * The type Duke exception.
 * Illegal argument exceptions for FindCommand.
 */
public class FindException extends Exception {

    /**
     * Instantiates a new Find exception.
     *
     * @param errorMessage the error message
     */
    public FindException(String errorMessage) {
        super("OOPS!! " + errorMessage);
    }
}
