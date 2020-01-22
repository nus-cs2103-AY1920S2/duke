public class Task {
    protected String taskName;
    protected boolean isDone;
    protected TaskType taskType = TaskType.OTHER;

    protected Task(String taskName, boolean isDone) {
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

    public TaskType getTaskType() {
        return taskType;
    }

    public void markDone() {
        isDone = true;
    }

    public String getTaskIcon() {
        return "O";
    }

    @Override
    public String toString() {
        return String.format("[%s] [%s]%s", getTaskIcon(), getStatusIcon(), taskName);
    }
}
