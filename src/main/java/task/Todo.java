package task;

public class Todo extends Task {

    public Todo(String taskAction) {
        super(taskAction);
    }

    /**
     * Returns a String representation of the todo task in the task list.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns the String representation of a todo task in file.
     *
     * @return String.
     */
    public String toStringForFileStorage() {
        return super.getStatus() ? String.format("T | 1 | %s", super.getTaskAction())
                : String.format("T | 0 | %s", super.getTaskAction());
    }
}
