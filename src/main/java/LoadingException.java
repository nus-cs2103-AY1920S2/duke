/**
 * An exception thrown when an error is encountered while loading tasks.
 */
public class LoadingException extends DukeException {
    @Override
    public String getMessage() {
        return "Error occurred while loading tasks from file.";
    }
}
