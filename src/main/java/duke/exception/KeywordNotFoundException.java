package duke.exception;

/**
 * Represents the exception thrown when the user inputs an invalid command not recognised by Duke.
 */
public class KeywordNotFoundException extends DukeException {
    public KeywordNotFoundException(String message){
        super(message);
    }
}