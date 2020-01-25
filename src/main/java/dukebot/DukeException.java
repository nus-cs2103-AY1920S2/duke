package dukebot;

public class DukeException extends Throwable {
    private LineName errorLineName = null;

    /**
     * Standard error message.
     */
    public DukeException(String message) {
        super(message);
    }

    public DukeException(LineName errorLineName) {
        super("Duke Error");
        this.errorLineName = errorLineName;
    }

    public LineName getErrorLineName() {
        return errorLineName;
    }
}