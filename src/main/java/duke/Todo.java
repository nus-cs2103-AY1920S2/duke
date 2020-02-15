package duke;

/**
 * Todo contains information about a given task with description.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s%s", super.toString(), super.getTagsAsStr());
    }

    @Override
    protected String getFileFormattedLine() {
        return String.format("T|%s|%s|%s", super.isDone ? "1" : "0", this.description, super.tags.toString());
    }
}
