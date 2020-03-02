package duke.commons;

import java.util.ArrayList;

/**
 * Represents a task that could be added.
 */

public class Task {
    protected String type;
    protected String description;
    protected boolean isDone;
    protected ArrayList<String> tagList;

    /**
     * Constructor for <code>Task</code>.
     * @param type <code>String</code> representing the type of the <code>Task</code>.
     * @param isDone true if the <code>Task</code> is completed, false otherwise.
     * @param description <code>String</code> representing the description of the <code>Task</code>.
     */
    public Task(String type, boolean isDone, String description) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
        this.tagList = new ArrayList<>();
    }

    /**
     * Returns the type of this <code>Task</code>.
     *
     * @return a string representation of the type of this <code>Task</code>.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the description of this <code>Task</code>.
     *
     * @return a string representation of the description of this <code>Task</code>.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a <code>String</code> representing whether this <code>Task</code> is done.
     *
     * @return a string representation of whether this <code>Task</code> is done.
     */
    public String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Returns a <code>String</code> object representing the type of this <code>Task</code>.
     *
     * @return the <code>String</code> "D", "E", or "T" correspondingly.
     */
    public String getTypeIcon() {
        return "";
    }

    /**
     * Marks the <code>Task</code> as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns an array of <code>String</code> objects representing this <code>Task</code>.
     *
     * @return a string array representation of the <code>Task</code> object.
     */
    public String[] toDataTokens() {
        String isDoneString = String.valueOf(this.isDone);
        return new String[] {isDoneString, this.description};
    }

    /**
     * Returns a <code>String</code> object representing this <code>Task</code>.
     *
     * @return a string representation of the <code>Task</code> object.
     */
    @Override
    public String toString() {
        return "[ " + this.getTypeIcon() + " ] [ " + this.getStatusIcon() + " ] " + this.description;
    }
}