package duke.task;

import java.io.Serializable;

public class Task implements Serializable {
    boolean taskCompleted;
    int taskNo;
    String taskName;

    Task(boolean taskCompleted, int taskNo, String taskName) {
        this.taskCompleted = taskCompleted;
        this.taskNo = taskNo + 1;
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        if (taskCompleted) {
            return taskNo + ".[✓] " + taskName;
        } else {
            return taskNo + ".[✗] " + taskName;
        }
    }
}
