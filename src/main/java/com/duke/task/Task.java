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

    public String generateWriteFormat(){
        String out = "";
        for (String t:tags) {
            out += t + ",";
        }
        return out;
    }

    public static LocalDate generateTime(String input) {
        return LocalDate.parse(input);
    }

    public void setDone() {
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setTag(String tag) {
        for (String t: tags) {
            if (t.equals(tag)) {
                return;
            }
        }
        tags.add(tag);

    }

    public List<String> getTags() {
        return tags;
    }

    public void removeTag(String tag) {
        tags.removeIf(a -> a.equals(tag));
    }

}
