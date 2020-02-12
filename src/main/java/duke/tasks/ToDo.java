package duke.tasks;

public class ToDo extends Task {

    public ToDo(String status, String description) {
        super(TaskType.TODO, status, description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
