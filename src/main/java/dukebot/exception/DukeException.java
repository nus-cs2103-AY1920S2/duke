package dukebot.exception;

import dukebot.ui.LineName;

public class DukeException extends Throwable {
    private LineName errorLineName;

    /**
     * Error with LineName.
     */
    public DukeException(LineName errorLineName) {
        super("Duke Error");
        this.errorLineName = errorLineName;
    }

    /**
     * Gets the LineName passed to the Exception.
     */
    public LineName getErrorLineName() {
        return errorLineName;
    }
}