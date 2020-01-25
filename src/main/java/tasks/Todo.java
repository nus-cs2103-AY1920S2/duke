package tasks;

public class Todo extends Task {
    private String taskType = "T";

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
