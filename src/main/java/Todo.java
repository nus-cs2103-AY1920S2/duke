/**
 * Represents the todo task, which has to be completed.
 * It contains a description of the task.
 */

public class Todo extends Task {
    public String taskType = "T";
    public Todo(String description) {
        super(description);
    }

    public Todo(String status, String description) {
        super(description);
        this.setStatus(status);
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveString() {
        return "T | " + super.saveString();
    }
}
