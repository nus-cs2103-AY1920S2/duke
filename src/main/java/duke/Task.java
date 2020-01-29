package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    private TaskType type = TaskType.TASK;
    protected String time;

    public Task(String description, String time) {
        this.description = description;
        this.isDone = false;
        this.time = time;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public TaskType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}