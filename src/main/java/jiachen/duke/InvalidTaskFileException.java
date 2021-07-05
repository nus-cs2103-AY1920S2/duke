package jiachen.duke;

/**
 * Exception thrown when file given to load from is invalid.
 */
public class InvalidTaskFileException extends DukeException {
    /**
     * Instantiates a new Invalid task file exception.
     */
    public InvalidTaskFileException() {
        super("Unable to load task file.");
    }
}
