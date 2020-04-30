package duke;

/**
 * The Task Class is the parent class to ToDo, Deadline and Event Classes.
 * @author qiujingying
 * @version 1.0
 */
public class Task {
    protected String description;
    protected boolean isDone;
    private TaskType type = TaskType.TASK;
    protected String time;

    /**
     * Creates a Task object with a description and time.
     * @param description details of the task
     * @param time time of the task
     */
    public Task(String description, String time) {
        this.description = description;
        this.isDone = false;
        this.time = time;
    }

    /**
     * Converts boolean isDone to a tick or cross.
     * @return a tick or cross as a String
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Changes the boolean isDone to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the type of the Task (ToDo, Event or Deadline).
     * @return type of task
     */
    public TaskType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}