package duke.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        super.taskType = TaskType.T;
    }

    public String toString() {
        return "[" + super.getTaskType() + "]" + super.toString();
    }

    public String saveString() {
        return getTaskType() + " | " + (getStatus() ? "1" : "0") + " | " + getDescription();
    }
}
