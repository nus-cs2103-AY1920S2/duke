/**
 * The DukeInvalidCommandException class extends the DukeException
 * class and it handles the invalid command format entered by the client.
 */
public class DukeInvalidCommandException extends DukeException {

    /**
     * Constructs a new DukeInvalidCommandException instance.
     *
     * @param message User-friendly error message
     */
    public DukeInvalidCommandException(String message) {
        super(message);
    }

    /**
     * Overrides the toString method to show the error message.
     *
     * @return The error message.
     */
    @Override
    public String toString() {
        return getMessage();
    }

}

