/**
 * Todo event that contains a description.
 */
public class Todo extends Task {

    /**
     * Creates a Todo event.
     * @param description description of the Todo event.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Formatted to save into hard disk.
     * @return a format that is standardised to be saved into the hard disk.
     */
    @Override
    public String saveToHardDiskFormat() {

        return String.format("T | %d | %s", this.completedCode, this.getDescription());
    }

    /**
     * Overrided to string method to show the type of task it is.
     * @return an extra [T] to denote that it is a Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
