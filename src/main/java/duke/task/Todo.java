package duke.task;

public class Todo extends Task {
    public Todo(boolean taskCompleted, int taskNo, String taskName) {
        super(taskCompleted, taskNo, taskName);
    }

    @Override
    public String toString() {
        if (taskCompleted) {
            return taskNo + ".[T][✓] " + taskName;
        } else {
            return taskNo + ".[T][✗] " + taskName;
        }
    }
}
