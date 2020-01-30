/**
 * Signals that a task has already been marked as done. <br>
 * This exception is thrown when a task that has been marked as done is marked again.
 */
public class DuplicateMarkAelitaException extends AelitaException {

    /**
     * Constructs a new instance of DuplicateMarkAelitaException.
     */
    public DuplicateMarkAelitaException() {
        super();
    }
}
