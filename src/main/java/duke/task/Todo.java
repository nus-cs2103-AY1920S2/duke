package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        super.taskType = TaskType.T;
    }

    @Override
    public String toString() {
        return "[" + super.getTaskType() + "]" + super.toString();
    }

    @Override
    public String saveString() {
        return getTaskType() + " | " + (getStatus() ? "1" : "0") + " | " + getDescription();
    }
}
