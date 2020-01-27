package dukebot.tasklist;

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
