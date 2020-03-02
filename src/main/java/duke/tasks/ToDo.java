package duke.tasks;

import java.util.ArrayList;

import duke.tags.Tag;

/**
 * Represents a ToDo task.
 *
 * @author Firzan Armani
 */
public class ToDo extends Task {
    /**
     * ToDo constructor.
     *
     * @param taskName Name of the ToDo task
     * @param tags ArrayList of Tags for the task
     */
    public ToDo(String taskName, ArrayList<Tag> tags) {
        super(taskName);
        this.setTags(tags);
    }

    /**
     * A method to return the details of the current Task object.
     * Contains the task done status and the task name.
     *
     * @return A String representation of the task details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }
}