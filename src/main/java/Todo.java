/**
 * Represents a task to be completed.
 * A <code>Todo</code> object contains a description of the task e.g., <code>borrow book</code>
 */
public class Todo extends Task {
    public Todo(String command) {
        super(command);
    }

    /**
     * Returns information about the task, stating that it is T(Todo) task, whether it is marked done and the
     * description of the task, for the updating of the file saved in hard disk.
     *
     * @return information about the task.
     */
    public String updateFile() {
        return "T - " + getDoneInt() + " - " + getCommand();
    }

    /**
     * Updates the command details for the Todo task.
     *
     * @param details command to be updated.
     */
    public void updateDetails(String details) {
        this.command = details;
    }

    @Override
    public String toString() {
        return "[T][" + getDoneSymbol() + "] " + getCommand();
    }
}
