/**
 * Represents the To-Do task.
 */
public class ToDo extends Task {

    /**
     * Constructor.
     * @param taskname
     */
    public ToDo(String taskname) {
        super(taskname, "T");
    }

    @Override
    /**
     * String representation of the task.
     * @return String.
     */
    public String toString() {
        String message = "";
        if (this.getDone()) {
            message += "[" + this.Tasktype + "]" + "[" + "\u2713" + "] " + this.taskname;
        } else {
            message += "[" + this.Tasktype + "]" + "[" + "\u2718" + "] " + this.taskname;
        }
        return message;
    }
}
