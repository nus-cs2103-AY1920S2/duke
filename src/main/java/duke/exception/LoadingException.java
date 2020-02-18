package duke.exception;

/**
 * An exception thrown when an error is encountered while loading tasks.
 */
public class LoadingException extends DukeException {
    String path = "";

    public LoadingException() {
    }

    public LoadingException(String path) {
        this.path = path;
    }

    @Override
    public String getMessage() {
        return "Error occurred while loading tasks from file.\n" + this.path;
    }
}
