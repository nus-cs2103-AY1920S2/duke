public class Todo extends Task{
    public Todo(String description) {
        super(description, false);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getSaveRepresentation() {
        return "T|||" + getIsDone() + "|||" + getDescription() + "\n";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", "T", (getIsDone() ? "\u2713" : "\u2718"), getDescription());
    }
}
