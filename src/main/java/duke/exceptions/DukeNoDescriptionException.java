package duke.exceptions;

/**
 * Handles event where command to create task has no description.
 */
public class DukeNoDescriptionException extends DukeException {
    public DukeNoDescriptionException(String taskName) {
        super.message = "You need to specify more details for task " + taskName + "!!";
    }
}
