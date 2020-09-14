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

    public ToDo(String taskName, int priority) {
        super(taskName, "T", priority);
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
        message += " Priority: " + this.getPriorityString();
        return message;
    }
}
