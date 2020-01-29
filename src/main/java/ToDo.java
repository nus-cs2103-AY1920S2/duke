/**
 * Represents the To-Do task.
 */
public class ToDo extends Task {

    /**
     * Constructor.
     * @param taskName
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
            message += "[" + this.getTaskType() + "]" + "[" + "\u2713" + "] " + this.getTaskName();
        } else {
            message += "[" + this.getTaskType() + "]" + "[" + "\u2718" + "] " + this.getTaskName();
        }
        return message;
    }
}
