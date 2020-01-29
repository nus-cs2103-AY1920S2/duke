public class Todo extends Task {
    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toStorageString() {
        return String.format("T | %s | %s\n", super.getStatusInteger(), super.getDesc());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
