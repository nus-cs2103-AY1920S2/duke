package e0148811.duke;

public class Task {
    static String TICK = "\u2714";
    static String CROSS = "\u2718";
    private boolean isDone;
    private String task;

    public Task(String description) {
        isDone = false;
        task = description;
    }

    public String getTask() {
        return task;
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[" + Task.TICK + "] " + task;
        } else {
            return "[" + Task.CROSS + "] " + task;
        }
    }
}
