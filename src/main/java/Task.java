public class Task {
    String taskName;

    private Task(String taskName) {
        this.taskName = taskName;
    }

    public static Task createTask(String taskName) {
        return new Task(taskName);
    }

    public String getTaskName() {
        return taskName;
    }
}
