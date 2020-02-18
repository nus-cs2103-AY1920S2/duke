package duke.tasks;

import java.util.ArrayList;

/**
 * Represents a task.
 * Task has a description and can be set to completion.
 */
public abstract class Task {
    /** Description of task. */
    private String description;
    /** Status of task. */
    private boolean isDone;
    /** List of all tags. */
    private ArrayList<String> tags;

    /**
     * Creates a task layer with description and a not done status.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Converts task into format used in save file.
     *
     * @return Save format of task in a string for easier back-conversion.
     */
    public abstract String toSaveFormat();

    @Override
    public String toString() {
        String checkbox = this.isDone ? "O" : "X";
        return String.format("[%s] %s", checkbox, this.description);
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets status of completion of task.
     * @return Status of task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Gets description of task.
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Adds tag to task.
     * @param tag Tag to be added to task.
     */
    public void addTag(String tag) {
        if (!tag.equals("")) {
            tags.add(tag);
        }
    }

    /** Checks if task is tagged with given tag.
     * @param tag Tag to be filtered.
     * @return True if task is tagged with tag.
     */
    public boolean containTag(String tag) {
        for (String t : tags) {
            if (t.equals(tag)) {
                return true;
            }
        }
        return false;
    }

    public String stringifyTagsToSaveFormat() {
        return String.join(" | ", this.tags);
    }
}
