package duke.exception;

/**
 * Represents the exception thrown when the user inputs an invalid command not recognised by Duke.
 */
public class TaskIndexException extends DukeException {
    public TaskIndexException(String message){
        super(message);
    }
}