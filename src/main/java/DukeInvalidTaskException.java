/**
 * The DukeInvalidTaskException class extends the DukeException
 * class and it handles the non-existent task enquired by the client.
 */
public class DukeInvalidTaskException extends DukeException {

    /**
     * Constructs a new DukeInvalidTaskException instance.
     *
     * @param message User-friendly error message
     */
    public DukeInvalidTaskException(String message) {
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

