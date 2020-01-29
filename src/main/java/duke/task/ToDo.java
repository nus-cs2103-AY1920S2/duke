package duke.task;

/** A To-Do task. */
public class ToDo extends Task {

    /**
     * Constructs a To-Do task.
     *
     * @param taskName Name of the task.
     */
    public ToDo(String taskName) {
        super(taskName);
        this.taskType = "T";
    }

    /**
     * Constructs a To-Do task with additional details.
     *
     * @param taskName Name of the task.
     * @param isDone Indicates if the task is completed already.
     */
    public ToDo(String taskName, boolean isDone) {
        super(taskName.trim(), isDone);
        this.taskType = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}