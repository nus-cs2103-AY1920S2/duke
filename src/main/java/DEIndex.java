/**
 * Generates an error message for the situation where the given index for Remove or Done command is not suitable.
 */
public class DEIndex extends DukeException {
    public DEIndex(String task) {
        String msg;
        if (task.equals("delete")) {
            msg = "☹ OOPS!!! I'm sorry, but a suitable index is required to delete a task.";
        } else {
            msg = "☹ OOPS!!! I'm sorry, but a suitable index is required to mark a task as complete.";
        }
        super.errorMsg = msg;
    }
}
