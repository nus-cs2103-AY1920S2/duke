/**
 * Signals that there is not enough necessary words in the command. <br>
 * This exception is thrown when no description, index for done or delete, or missing date or time.
 */
public class InsufficientArgumentAelitaException extends AelitaException {

    /**
     * Constructs a new instance of InsufficientArgumentAelitaException.
     *
     * @param context the context that threw the exception.
     */
    public InsufficientArgumentAelitaException(String context) {
        super(context);
    }

}
