package duke.tasks;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        super.TYPE = TaskType.TODO;
    }

    public ToDo(String status, String description) {
        super(status, description);
        super.TYPE = TaskType.TODO;
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
