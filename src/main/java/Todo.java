public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    protected String getFileFormattedLine() {
        return String.format("T|%s|%s", super.isDone ? "1" : "0", this.description);
    }
}
