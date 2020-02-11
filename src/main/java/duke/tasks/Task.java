package duke.tasks;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.tags.Tag;

public class Task {
    private String taskName;
    private boolean taskDone;
    private ArrayList<Tag> tags;

    public Task(String taskName) {
        this.taskName = taskName;
        this.taskDone = false;
    }

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

    public boolean getTaskDone() {
        return this.taskDone;
    }

    public void setTaskDone() {
        this.taskDone = true;
    }

    /**
     * Return a string of the details of the current Task object.
     * Contains the task done status and the task name.
     *
     * @return A String representation of the task details.
     */
    public String toString() {
        return "[" + (getTaskDone()
                ? Character.toString(0x2713)
                : Character.toString(0x2717))
            + "] " + getTaskName();
    }

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