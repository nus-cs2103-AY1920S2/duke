public class DukeExceptionIndex extends DukeException {
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
