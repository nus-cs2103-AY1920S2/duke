package task;

import parser.TimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {

    protected String duration;

    /**
     * Constructor for Event Task.
     *
     * @param name the name of the Event task
     */
    public Event(String name, String duration) {
        super(name);
        this.duration = duration;
    }

    /**
     * Constructor for the Event Task.
     *
     * @param name the name of the Event task
     * @param isDone the boolean value representing the status of the Event task
     */
    public Event(String name, String duration, boolean isDone) {
        super(name, isDone);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.duration + ")";
    }

    /**
     * Returns a summary of the Event task in the file format.
     *
     * @return Returns a summary of the Event task in the file format
     */
    public String toStringFileFormat() {
        return "E" + super.toStringFileFormat()
                + " | " + this.duration;
    }
}
