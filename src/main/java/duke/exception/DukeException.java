package duke.exception;

/**
 * The type Duke exception
 * Illegal argument exceptions for duke.task.Event, duke.task.Deadline , To-do.
 */
public class DukeException extends Exception {

    /**
     * Instantiates a new Duke exception.
     *
     * @param errorMessage the error message
     */
    public DukeException(String errorMessage) {
        super("OOPS!! " + errorMessage);
    }

}
