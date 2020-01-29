package duke.task;

import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;
    protected final static DateTimeFormatter IN_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected final static DateTimeFormatter OUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy KK:mm a");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = TaskType.X;
    }

    public String getStatusIcon() {
        return (isDone ? "[y]": "[n]");
    }

    public boolean getStatus() {
        return isDone;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    public String getDescription() {
        return this.description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

    public String saveString() {
        return taskType + " | " + (isDone ? "1" : "0") + " | " + description + " | ";
    }
}
