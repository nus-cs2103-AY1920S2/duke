package tasks;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import tasks.Tag;

/**
 * Specifies main attributes and methods for tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Set<Tag> tags = new HashSet<>();

    /**
     * Creates a task object with a description.
     *
     * @param description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, Set<Tag> tags) {
        this.description = description;
        this.isDone = false;
        this.tags = tags;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    /**
     * Returns the description of the task.
     *
     * @return task description.
     */
    public String getDescription() {
        return description;
    }


    /**
     * Returns a tick for tasks marked as done and a cross for tasks not done.
     *
     * @return tick for tasks marked as done and a cross for tasks not done.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public boolean checkTags(String tag) {
        return tags.contains(new Tag(tag));
    }

    /**
     * Changes the status of the task to done.
     */
    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
