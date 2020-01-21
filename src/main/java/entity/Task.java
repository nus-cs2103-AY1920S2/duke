package entity;

public class Task {
    private boolean isDone;
    private String taskName;

    public Task(String taskName) {
        this.isDone = false;
        this.taskName = taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone(boolean done) {
        isDone = done;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
