package duke.tasks;

public class ToDo extends Task {

    public ToDo(String status, String description) {
        super(TaskType.TODO, status, description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public boolean changeDate(String update) {
        assert false; // this method should never be called for a todo class
        return false;
    }
}
