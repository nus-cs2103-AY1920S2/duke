public class Todo extends Task {

    public Todo(String description) {
        super(description, "2099-12-31");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}