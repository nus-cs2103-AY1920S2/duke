/**
 * The DukeDuplicateTaskException class extends the DukeException
 * class and it handles the duplicate task entered by the client.
 */
public class DukeDuplicateTaskException extends DukeException {

    /**
     * Constructs a new DukeDuplicateTaskException instance.
     *
     * @param message User-friendly error message
     */
    public DukeDuplicateTaskException(String message) {
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

