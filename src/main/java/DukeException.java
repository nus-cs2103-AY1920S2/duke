/**
 * The DukeException class extends the Exception class and
 * it handles the input error by the client, such as invalid
 * command, as well as non-existent task enquired
 * by the client.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException instance.
     *
     * @param message User-friendly error message
     */
    public DukeException(String message) {
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

