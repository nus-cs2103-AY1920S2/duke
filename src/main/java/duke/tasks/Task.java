package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String s) {
        this.description = s;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✗");
    }

    public String store() {
        return "Task|" + (isDone ? "1" : "0") + "|" + this.description;
    }

    public static Task create(String[] strArr) {
        Task t = new Task(strArr[2]);
        if (strArr[1].equals("1")) {
            t.setDone();
        }
        return t;
    }

    @Override
    public String toString() {
        return '[' + getStatusIcon() + ']' + this.description;
    }
}
