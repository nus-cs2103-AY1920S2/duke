package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Task contains information about a given task, including its description and done status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected ArrayList<String> tags;

    /**
     * Constructs a Task object. Starts out as undone.
     * 
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Gets the icon associated with the done status of the task. Check-mark for done and cross for undone.
     * 
     * @return Done status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");  // return tick or X symbols
    }

    /**
     * Gets the description of the task.
     * 
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Marks the task as being done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representing the task in the format for saving into a file.
     * 
     * @return Task represented as a string for saving into a file.
     */
    protected String getFileFormattedLine() {
        return String.format("G|%s|%s|%s", this.isDone ? "1" : "0", this.description, this.tags.toString());
    }

    /**
     * Returns a string representation of the task including its done status icon and description.
     * 
     * @return String representing the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
    
    protected String getTagsAsStr() {
        return (this.tags.isEmpty() ? "" : " " + String.join(" ", this.tags));
    }
    
    protected void addTag(String tag) {
        this.tags.add("#" + tag);
    }
    
    protected void setTags(List<String> tags) {
        this.tags = new ArrayList<>(tags);
    }
}
