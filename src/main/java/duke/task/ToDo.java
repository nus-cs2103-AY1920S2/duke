package duke.task;

public class ToDo extends Task {

    /**
     * Constructor for a To-Do task.
     *
     * @param taskName Name of the task.
     */
    public ToDo(String taskName) {
        super(taskName);
        this.taskType = "T";
    }

    /**
     * Overloaded constructor for a To-Do task.
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