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
    protected Name name;
    protected boolean isDone;
    protected Set<Tag> tags;

    /**
     * Creates a task object with a name.
     *
     * @param name of the task.
     * @param tags tags if present.
     */
    public Task(Name name, Set<Tag> tags) {
        this.name = name;
        this.isDone = false;
        this.tags = tags;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    /**
     * Returns the name of the task.
     *
     * @return task name.
     */
    public Name getName() {
        return name;
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
        return String.format("[%s] %s", getStatusIcon(), name);
    }
}
