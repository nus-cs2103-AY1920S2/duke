package task;

public class Task {

    private String taskAction;
    private boolean isDone;

    public Task(String taskAction) {
        this.taskAction = taskAction;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public String getTask() {
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
