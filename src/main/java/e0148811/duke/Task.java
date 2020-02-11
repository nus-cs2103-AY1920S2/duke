package e0148811.duke;

public class Task {
    static String DONE = "Done";
    static String NOT_DONE = "Not Done";
    private boolean isDone;
    private String task;

    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        task = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[" + Task.DONE + "] " + task;
        } else {
            return "[" + Task.NOT_DONE + "] " + task;
        }
    }

    public String toSimplerString() {
        String isDone;
        if (this.isDone) {
            isDone = "T";
        } else {
            isDone = "F";
        }
        return isDone + "//" + getDescription();
    }

    public String getDescription() {
        return task;
    }
}
