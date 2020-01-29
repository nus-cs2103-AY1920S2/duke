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
            msg = "☹ OOPS!!! I'm sorry, but a suitable index is required to delete a task.";
        } else {
            msg = "☹ OOPS!!! I'm sorry, but a suitable index is required to mark a task as complete.";
        }
        super.errorMsg = msg;
    }
}
