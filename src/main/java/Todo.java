public class Todo extends Task {
    public Todo(String desc) {
        super(desc);
    }

    public Todo(String desc, boolean isDone) {
        super(desc);
        super.isDone = isDone;
    }

    @Override
    public String generateSaveFileEntry() {
        return String.format("T | %d | %s\n", this.getStatusAsInt(), this.description);
    }

    @Override
    public String toString() {
        return String.format("[T] [%s] %s", this.getStatus(), this.description);
    }
}
