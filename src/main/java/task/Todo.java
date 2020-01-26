package task;

public class Todo extends Task {
    /**
     * Constructor for task.Todo
     * @param description description of task.Todo
     */
    public Todo(String description) {
        super(description);
        type = "todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
