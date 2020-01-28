package dukeApp.files;

public class DukeException extends Throwable {
    protected String task;

    /**
     * Returns an error message if the command does not exist
     *
     * @param task command
     * @return command error message
     */
    public String errorMsg(String task) {
        if (task.equals("todo") || task.equals("deadline") || task.equals("event")) {
            return "☹ OOPS!!! The description of a " +task+ " cannot be empty.";
        }
        else {
            return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }

    /**
     * Returns an error message if the list is empty.
     *
     * @returne list empty error message
     */
    public String empty() {
        return "There is no task in the list.";
    }

    /**
     * Returns an error message if the task number does not exist in a list
     * @return Non-existent task error message
     */
    public String outOfBound() {
        return "No such task exist.";
    }
}
