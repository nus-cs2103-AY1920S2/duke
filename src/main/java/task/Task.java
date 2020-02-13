package task;

import java.time.LocalDateTime;

public class Task {

    private String taskAction;
    private boolean isDone;
    protected int taskDuplicatesNumber;

    public Task(String taskAction) {
        this.taskAction = taskAction;
        this.isDone = false;
        this.taskDuplicatesNumber = 1;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public String getTaskAction() {
        return this.taskAction;
    }

    @Override
    public String toString() {
        if (isDone) {
            return String.format("[^] %s", this.taskAction);
        } else {
            return String.format("[x] %s", this.taskAction);
        }
    }

    public String toStringForFileStorage() {
        return "";
    }
}
