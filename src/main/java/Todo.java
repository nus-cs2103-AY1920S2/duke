/**
 *
 */

public class Todo extends Task {
    /**
     * Constructor for a new Todo object.
     * @param taskType the type of Task.
     * @param isDone whether the Task is marked as done.
     * @param taskName the name of the Task.
     */
    public Todo(char taskType, boolean isDone, String taskName) {
        super(taskType, isDone, taskName, "");
    }
}
