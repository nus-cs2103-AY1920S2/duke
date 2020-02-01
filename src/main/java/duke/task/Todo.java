package duke.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Creates an instance of Todo.
     *
     * @param description description for the Todo
     */
    public Todo(String description) {
        super(description);
        super.taskType = TaskType.T;
    }

    @Override
    public String saveString() {
        return getTaskType() + " | " + (getStatus() ? "1" : "0") + " | " + getDescription();
    }

    @Override
    public String toString() {
        return "[" + super.getTaskType() + "]" + super.toString();
    }
}
