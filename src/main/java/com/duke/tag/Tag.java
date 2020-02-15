package com.duke.tag;

import com.duke.task.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a tag that can be associated with objects or treated as the title of a collection of
 * tasks.
 */
public class Tag {
    String tagName;
    List<Task> tasks = new ArrayList<>();

    /**
     * Instantiates a tag with the name specified by the input string.
     * @param name the name of the tag.
     */
    public Tag(String name) {
        tagName = name;
    }

    /**
     * Gets the name of the tag.
     * @return The name of the tag.
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * Adds a task to the collection of tasks that has the tag.
     * @param task The task to be added.
     */
    public void addTaskToTag(Task task) {
        if (!tasks.contains(task)) {
            tasks.add(task);
        }
    }

    /**
     * Gets the list of tasks that have this tag.
     * @return The list of tasks that have this tag.
     */
    public List<Task> getTasks() {
        return tasks;
    }
}
