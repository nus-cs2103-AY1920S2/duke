package duke.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is a simulation of tasks.
 */
public abstract class Task {
    /** Description of this task. */
    protected String description;
    /** Status of this task, whether it has been marked as done. */
    protected boolean isDone;
    protected List<String> tags;

    /**
     * Class constructor with status to be false by default.
     *
     * @param description Description of the task.
     * @param tags Existing tags of the task.
     */
    public Task(String description, String... tags) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
        if (tags.length != 0) {
            String[] currTags = tags[0].split(",");
            this.tags.addAll(Arrays.asList(currTags));
        }
    }

    /**
     * Returns status of the task.
     *
     * @return Status icon, tick for done tasks and X for undo tasks.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns if current task has tags.
     *
     * @return true if current task has tags. Otherwise return false.
     */
    public boolean hasTags() {
        return this.tags.size() > 0;
    }

    /**
     * Add tag to current task.
     *
     * @param tag New tag.
     */
    public void addTags(String tag) {
        this.tags.add(tag);
    }

    /**
     * Returns tags of the task.
     *
     * @return number of tags, separated by |.
     */
    private String getTagsIcon() {
        if (this.tags.size() == 0) {
            return "";
        }

        String str = "";
        if (hasTags()) {
            for (int i = 0; i < this.tags.size(); i++) {
                str = str.concat(this.tags.get(i)
                        + (i == (this.tags.size() - 1) ? "" : ","));
            }
        }
        return str;
    }

    /**
     * Overrides String representation of tasks.
     *
     * @return  String representation of status and description.
     */
    @Override
    public String toString() {
        String tags = getTagsIcon();
        return "[" + getStatusIcon() + "] " + getDescription()
                + (tags.length() == 0 ? "" : "  #<" + getTagsIcon() + ">");
    }

    /**
     * Returns description of the task.
     *
     * @return Description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks this task status as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets String representation of the task when storing in data file.
     *
     * @return String representation of the task data
     */
    public String getData() {
        return "|" + (this.isDone ? 1 : 0) + "|" + this.description
                + "|" + getTagsIcon();
    }
}
