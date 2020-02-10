package duke.exception;

/**
 * Generates an error message for the situation where the given index for Remove or Done command is not suitable.
 */
public class DukeExceptionIndex extends DukeException {
    /**
     * Generates the appropriate error message.
     * @param task Task that the Exception was made at.
     */
    public DukeExceptionIndex(String task) {
        String msg;
        if (task.equals("delete")) {
            msg = "I need a valid index to delete them.";
        } else {
            msg = "I need a valid index to find which item to mark as done.";
        }
        super.errorMsg = msg;
    }
}
