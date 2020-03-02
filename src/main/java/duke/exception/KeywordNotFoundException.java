package duke.exception;

/**
 * Represents the exception thrown when the user inputs a keyword that's not found.
 */
public class KeywordNotFoundException extends DukeException {
    /**
     * Constructs a fresh instance of a keyword not found exception.
     * @param message The exception message.
     */
    public KeywordNotFoundException(String message){
        super(message);
    }
}