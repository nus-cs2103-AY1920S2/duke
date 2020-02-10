package task;

/**
 * The simple implementation of task.Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a new instance of task.Todo.
     *
     * @param description the description of the task.
     */
    public Todo(String description) {

        super(description);
    }

    @Override
    public String toString() {

        return "[T]" + super.toString();
    }

}
