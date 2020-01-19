public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String newTask) {
        this.taskName = newTask;
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String markDone() {
        if (this.isDone) {
            return "Oops! This was already marked as done!";
        }
        this.isDone = true;
        return "Marked this task as done: " + taskName;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + this.taskName;
    }
}
