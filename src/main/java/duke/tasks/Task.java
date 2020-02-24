package duke.tasks;

import java.util.ArrayList;
import duke.tags.Tag;

/**
 * Class to represent a generic Task object.
 *
 * @author Firzan Armani
 */
public class Task {
    private String taskName;
    private boolean taskDone;
    private ArrayList<Tag> tags;

    /**
     * Task constructor.
     *
     * @param taskName Description of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.taskDone = false;
        this.tags = new ArrayList<Tag>();
    }

    /**
     * Getter for the name of the Task.
     *
     * @return The name of the Task
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Getter for the Done state of the Task.
     *
     * @return The boolean of the Task Done status
     */
    public boolean getTaskDone() {
        return this.taskDone;
    }

    /**
     * Setter for the Done state of the Task.
     * Set the Task to Done.
     */
    public void setTaskDone() {
        this.taskDone = true;
    }

    /**
     * Setter for adding tags to the Task.
     *
     * @param tags Variable number of tags to add to the Task
     */
    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public ArrayList<Tag> getTags() {
        return this.tags;
    }

    public String tagsString() {
        String tagsString = " ";
        boolean first = true;
        for (Tag tag : this.tags) {
            if (first) {
                tagsString = tag.toString();
                first = false;
            } else {
                tagsString += ", " + tag;
            }
        }
        return tagsString.equals(" ") ? "" : tagsString;
    }

    /**
     * Return a string of the details of the current Task object.
     * Contains the task done status and the task name.
     *
     * @return A String representation of the task details
     */
    public String toString() {
        String tagsString = tagsString();
        if (!tagsString.equals("")) {
            tagsString = " (Tagged with: "  + tagsString + ")";
        }
        return "[" + (getTaskDone()
                ? Character.toString(0x2713)
                : Character.toString(0x2717))
            + "] " + getTaskName() + tagsString;
    }

    /**
     * Return a string of the current Task object to be saved into the storage file.
     *
     * @return A string representation of the task details for saving into the storage file
     */
    public String toFileString() {
        return " | " + getTaskDone()
            + " | " + getTaskName()
            + " | " + tagsString();
    }
}