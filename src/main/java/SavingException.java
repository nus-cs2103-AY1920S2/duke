/**
 * An exception thrown when an error is encountered while saving tasks.
 */
public class SavingException extends DukeException {
    @Override
    public String getMessage() {
        return "Error occurred while saving tasks to file.";
    }
}
