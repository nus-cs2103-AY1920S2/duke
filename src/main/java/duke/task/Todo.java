package duke.task;

/**
 * <h1>Todo Class</h1>
 * A subclass of Task class. Record the description of the todo task.
 *
 * @author Eng Xuan En
 */
public class Todo extends Task {
    /**
     * Class constructor for Todo which takes in the description of the task in String format.
     *
     * @param description description of Todo
     */
    public Todo(String description) {
        super(description);
        type = "todo";
    }

    /**
     * Return the task details in [T][Y or N] {description of the task} format.
     *
     * @return task details in String format
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
