/**
 * Represents the To-Do task.
 */
public class ToDo extends Task {

    /**
     * Constructor.
     * @param taskName name of the given task.
     */
    public ToDo(String taskName) {
        super(taskName, "T");
    }

    @Override
    /**
     * String representation of the task.
     * @return String.
     */
    public String toString() {
        String message = "";
        if (this.getDone()) {
            message += "[" + this.getTaskType() + "]" + "[" + "✓" + "] " + this.getTaskName();
        } else {
            message += "[" + this.getTaskType() + "]" + "[" + "✗" + "] " + this.getTaskName();
        }
        return message;
    }
}
