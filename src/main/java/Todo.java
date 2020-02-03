public class Todo extends Task {
    public Todo(String description) {
        super(description, "");
    }

    @Override
    public String getTypeName() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStringFile() {
        return "T" + " | " + super.toStringFile();
    }
}
