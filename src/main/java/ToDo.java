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
            message += "[" + this.taskType + "]" + "[" + "\u2713" + "] " + this.taskName;
        } else {
            message += "[" + this.taskType + "]" + "[" + "\u2718" + "] " + this.taskName;
        }
        return message;
    }
}
