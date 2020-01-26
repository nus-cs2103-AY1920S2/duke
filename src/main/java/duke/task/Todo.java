package duke.task;

public class Todo extends Task {
    /**
     * Constructor for duke.task.Todo
     * @param description description of duke.task.Todo
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
