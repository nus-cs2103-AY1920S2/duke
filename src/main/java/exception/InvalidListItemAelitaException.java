package exception;

/**
 * Signals that an invalid item is requested from main.TaskList. <br>
 * This exception is thrown when attempting to use an out of bound index to access a main.TaskList instance.
 */
public class InvalidListItemAelitaException extends AelitaException {

    /**
     * Constructs a new instance of exception.InvalidListItemAelitaException.
     */
    public InvalidListItemAelitaException() {

        super();
    }

}
