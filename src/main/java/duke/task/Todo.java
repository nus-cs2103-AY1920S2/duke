package duke.task;

public class Todo extends Task {
    /**
     * Constructs a new to-do.
     *
     * @param description the details of the to-do
     */
    public Todo(String description) {
        this(description, false);
    }

    private Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public Todo markDone() {
        return new Todo(description, true);
    }

    @Override
    public String serialize() {
        return serialize("T");
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toFormatString() {
        return toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof Todo;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}