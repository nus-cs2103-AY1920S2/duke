public class ToDo extends Task {
    ToDo(String description) {
        super(description);
    }

    ToDo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
