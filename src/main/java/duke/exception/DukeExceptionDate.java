package duke.exception;

/**
 * Generates an error message for the situation where a date was not given for a Deadline or Event Task.
 */
public class DukeExceptionDate extends DukeException {
    public DukeExceptionDate(String task) {
        super.errorMsg = "I need a date for " + task + ". Did you specify one with the tag?";
    }
}
