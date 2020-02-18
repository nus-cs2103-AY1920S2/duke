package exception;

/**
 * The base exception used by the project.
 */
public class AelitaException extends Exception {

    /**
     * Constructs a new exception.AelitaException with null as message.
     */
    public AelitaException() {

        super();
    }

    /**
     * Constructs a new exception.AelitaException with specified message.
     *
     * @param msg the specified message details.
     */
    public AelitaException(String msg) {

        super(msg);
    }

}
