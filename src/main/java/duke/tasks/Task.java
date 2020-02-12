package duke.tasks;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    }

    /**
     * Getter for the name of the Task.
     *
     * @return The name of the Task
     */
    public Task(String taskName, Tag... tags) {
        this.taskName = taskName;
        this.taskDone = false;
        this.tags = new ArrayList<Tag>();
        for (Tag tag : tags) {
            this.tags.add(tag);
        }
    }

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
     * Return a string of the details of the current Task object.
     * Contains the task done status and the task name.
     *
     * @return A String representation of the task details
     */
    public String toString() {
        return "[" + (getTaskDone()
                ? Character.toString(0x2713)
                : Character.toString(0x2717))
            + "] " + getTaskName();
    }

    /**
     * Return a string of the current Task object to be saved into the storage file.
     *
     * @return A string representation of the task details for saving into the storage file
     */
    public String toFileString() {
        return " | " + getTaskDone()
            + " | " + getTaskName();
    }

    public static void getTags(String input) {
        Pattern pat = Pattern.compile("\\#(\\w+)");
        Matcher mat = pat.matcher(input);
        ArrayList<Tag> tags = new ArrayList<Tag>();
        while (mat.find()) {
            tags.add(new Tag(mat.group()));
            // System.out.println(mat.group().toString());
        }
        System.out.println(tags.size());
    }
}