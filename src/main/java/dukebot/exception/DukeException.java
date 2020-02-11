package dukebot.exception;

import dukebot.ui.LineName;

/**
 * Generic exception.
 */
public class DukeException extends Throwable {
    private final LineName errorLineName;

    /**
     * Error with LineName.
     *
     * @param errorLineName The LineName of the line with the error message.
     */
    public DukeException(LineName errorLineName) {
        super("Duke Error");
        this.errorLineName = errorLineName;
    }

    /**
     * Gets the LineName passed to the Exception.
     *
     * @return The LineName stored in this exception.
     */
    public LineName getErrorLineName() {
        return errorLineName;
    }
}