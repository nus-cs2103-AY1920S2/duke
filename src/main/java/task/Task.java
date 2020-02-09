package task;

public class Task {
    private String task;
    private boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public void finishTask() {
        this.isDone = true;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        if (isDone) {
            return String.format("[^] %s", this.task);
        } else {
            return String.format("[x] %s", this.task);
        }
    }

    public String toFormatString() {
        return "";
    }
}
