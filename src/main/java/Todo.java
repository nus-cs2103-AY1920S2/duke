/**
 * The Todo object extends the Task object in creating a task of type todo.
 */

public class Todo extends Task {
    /**
     * Constructor for a new Todo object.
     * @param isDone whether the Task is marked as done.
     * @param taskName the name of the Task.
     */
    public Todo(boolean isDone, String taskName) {
        super('T', isDone, taskName, "");
    }
}
