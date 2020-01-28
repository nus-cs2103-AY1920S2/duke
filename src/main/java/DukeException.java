// TODO: Will expand exception hierarchy in future

public class DukeException extends Exception {
    /**
     * Constructs a new custom exception.
     *
     * @param message the message of the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}