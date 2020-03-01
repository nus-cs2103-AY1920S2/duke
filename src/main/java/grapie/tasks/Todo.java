package grapie.tasks;

public class Todo extends Task {
    /**
     * Constructor for Grapie.Tasks.Todo.
     *
     * @param description The description for the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
