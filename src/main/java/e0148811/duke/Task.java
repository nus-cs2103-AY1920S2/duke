package e0148811.duke;

public class Task {
    private boolean isDone;
    private String task;

    Task(String task) {
        isDone = false;
        this.task = task;
    }

    String getTask() {
        return task;
    }

    boolean isDone() {
        return isDone;
    }
}
