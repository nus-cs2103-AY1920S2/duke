public class ToDo extends Task {

    ToDo(String description) {
        super(description);
        super.TYPE = TaskType.TODO;
    }
    ToDo(String status, String description) {
        super(status, description);
        super.TYPE = TaskType.TODO;
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
