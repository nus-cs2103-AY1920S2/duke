package util.task;

import util.Parser;

import java.util.Date;

/**
 * Represents an event.
 * An event has a description, a date, and a true or false on whether it is completed.
 */
public class EventTask extends Task {
    Date deadline;

    /**
     * Creates a util.task.EventTask object.
     *
     * @param description The String description of the event.
     * @param isDone      The boolean variable on whether the event is completed.
     * @param deadline    The due Date of the event.
     */
    private EventTask(String description, boolean isDone, Date deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Static method to create an util.task.EventTask object.
     * Throws an exception if the input text is not in the required format.
     *
     * @param description The String description of the event.
     * @param deadline    The String of the date and time of the event.
     * @return An Event.
     */
    public static EventTask createEventTask(String description, Date deadline) {
        assert description != null;
        assert deadline != null;

        return new EventTask(description.trim(), false, deadline);
    }

    /**
     * Sets this util.task.EventTask's complete status to true.
     * Overrides the abstract method in util.task.Task.
     *
     * @return A new complete util.task.EventTask with the same description, deadline as the util.task.EventTask instance.
     */
    @Override
    public EventTask completeTask() {
        return new EventTask(this.description, true, this.deadline);
    }

    @Override
    public String toString() {
        return "[E] [" + super.getStatusIcon() + "] " + this.description
                + " (at: " + Parser.dateToString(this.deadline) + ")";
    }
}