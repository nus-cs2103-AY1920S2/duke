package com.duke.task;

import com.duke.tag.Tag;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a task specified by the user.
 */
public abstract class Task {
    protected String description;
    private boolean isDone;
    private List<String> tags = new ArrayList<>();

    public Task(String d) {
        this.description = d;
        this.isDone = false;
    }

    /**
     * Generates the icon representation of the status of the task.
     *
     * @return the icon representation of the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Generates the format used when storing the task information into the storage.
     * @return String representation of the information of this task to be stored in storage.
     */
    public String generateWriteFormat() {
        String out = "";
        for (String t:tags) {
            out += t + ",";
        }
        return out;
    }

    /**
     * Generates a LocalDate format of the time specified for the task from a string input.
     * @param input String representation of the time.
     * @return LocalDate representation of the time.
     */
    public static LocalDate generateTime(String input) {
        return LocalDate.parse(input);
    }

    /**
     * Sets this task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Checks if the task is done.
     * @return Returns true if the task is already done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Associates this task with a given tag.
     * @param tag The tag to associate the task with.
     */
    public void setTag(String tag) {
        for (String t: tags) {
            if (t.equals(tag)) {
                return;
            }
        }
        tags.add(tag);
    }

    /**
     * Gets the list of tags of the task.
     * @return The list of tags of the task.
     */
    public List<String> getTags() {
        return tags;
    }

}
