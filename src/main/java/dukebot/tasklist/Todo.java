package dukebot.tasklist;

/**
 * Represents a Todo.
 */
public class Todo extends Task {

    /**
     * Generates a new Todo.
     *
     * @param description  The name of the Todo.
     */
    public Todo(String description) {
        super(description, TaskType.TODO,null);
    }
}
