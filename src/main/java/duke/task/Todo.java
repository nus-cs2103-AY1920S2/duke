package duke.task;

/**
 * Represents a todo task that extends from the <code>Task</code> class.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task instance. The todo task is initialized as undone.
     * @param description The description of the todo task details.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task in the data file.
     * @return string representation of the todo task in the data file.
     */
    @Override
    public String toStringInFile() {
        return "T # " + (isDone ? "1" : "0") + " # " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}