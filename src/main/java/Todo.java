/**
 * Represents a task to be completed.
 * A <code>Todo</code> object contains a description of the task e.g., <code>borrow book</code>
 */
public class Todo extends Task {
    public Todo(String command) {
        super(command);
    }

    /**
     * Returns whether the task is marked done in int form instead of symbol form, to be displayed in the file
     * saved in hard disk.
     *
     * @return 1 if task is marked done, or 0 if the task is marked undone.
     */
    public int getDoneInt() {
        return isDone() ? 1 : 0;
    }

    /**
     * Returns information about the task, stating that it is T(Todo) task, whether it is marked done and the
     * description of the task, for the updating of the file saved in hard disk.
     *
     * @return information about the task.
     */
    @Override
    public String updateFile() {
        return "T - " + getDoneInt() + " - " + getCommand();
    }

    @Override
    public String toString() {
        return "[T][" + getDoneSymbol() + "] " + getCommand();
    }
}
