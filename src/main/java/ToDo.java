public class ToDo extends Task {
    ToDo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
