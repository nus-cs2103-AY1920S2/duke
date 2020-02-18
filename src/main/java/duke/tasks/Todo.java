package duke.tasks;

public class Todo extends Task {
    /** Creates a Todo with the given description.
     *
     * @param description Description of task.
     * @param tags Taggings of task.
     */
    public Todo(String description, String...tags) {
        super(description);
        for (String tag : tags) {
            super.addTag(tag);
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts task into format used in save file.
     *
     * @return Save format of task in a string for easier back-conversion.
     */
    @Override
    public String toSaveFormat() {
        char d = super.getIsDone() ? '1' : '0';
        return "T | " + d + " | " + super.getDescription() + " | " + super.stringifyTagsToSaveFormat();
    }
}
