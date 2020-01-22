public class Task {
    private String taskName;
    private boolean isDone;

    private Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public static Task createTask(String taskName) {
        return new Task(taskName, false);
    }

    public String getTaskName() {
        return taskName;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markDone() {
        isDone = true;
    }
}
