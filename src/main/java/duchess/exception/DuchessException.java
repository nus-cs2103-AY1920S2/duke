package duchess.exception;

/**
 * The {@code DuchessException} class helps to handle exceptions encountered
 * throughout the {@code Duchess} program.
 */
public class DuchessException extends Exception {
    /**
     * Initialises an instance of {@code DuchessException} with the
     * given errorMessage.
     *
     * @param errorMessage Message of the error.
     */
    public DuchessException(String errorMessage) {
        super(errorMessage);
    }
}
