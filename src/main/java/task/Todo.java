package task;

public class Todo extends Task {

    public Todo(String taskAction) {
        super(taskAction);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    public String toStringForFileStorage() {
        return super.getStatus() ? String.format("T | 1 | %s", super.getTaskAction())
                : String.format("T | 0 | %s", super.getTaskAction());
    }
}
