package duke.exception;

/**
 * Represents the exception thrown when the user inputs a symbol reserved for Duke.
 */
public class ReservedSymbolException extends DukeException {
    /**
     * Constructs a fresh instance of a reserved symbol exception.
     * @param message The exception message.
     */
    public ReservedSymbolException(String message) {
        super(message);
    }
}