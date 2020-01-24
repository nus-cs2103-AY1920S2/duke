public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String status, String description) {
        super(description);
        this.setStatus(status);
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
