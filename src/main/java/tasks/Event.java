package tasks;

import tasks.Task;

/**
 * Represents a task occurring at a certain place.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for Event task.
     * @param description How the user describes the task.
     * @param at Location where the task takes place.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a string for printing.
     * @return String which represents the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
