package duke.exceptions;

/**
 * Exception when loan command has issue.
 */
public class LoanException extends DukeException {

    /**
     * Constructor for LoanException.
     *
     * @param message any message to convey.
     */
    public LoanException(String message) {
        super(message);
    }

    /**
     * Provides a custom String representation of the current Exception, which includes formatting lines.
     *
     * @return the presentable String representation.
     */
    @Override
    public String toString() {
        return "Loans should be tagged with someone's name and a value.\n"
                + "Please try again!";
    }

}
