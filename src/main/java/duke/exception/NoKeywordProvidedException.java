package duke.exception;

public class NoKeywordProvidedException extends DukeException {
    /**
     * Overrides the Object's toString method
     * and contains details of the error.
     * @return The String that containing reason for the error.
     */
    @Override
    public String toString() {
        return "Keyword has to be provided by finding certain tasks. Please try again.";
    }
}
