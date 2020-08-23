package duke.util;

import duke.exception.DukeInvalidDateFormatException;

/*
 * Event
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 21 Jan 2020
 *
 */

/**
 * The Event class extends the Task class and it defines
 * the properties that an event has.
 * @author Mario Lorenzo
 */

public class Event extends Task {
    private TaskSchedule atSchedule;

    /**
     * <p>Constructs an Event instance, given the description,
     * and the schedule when the event is happening.</p>
     * @param description The description of the event.
     * @param atSchedule The date of the event.
     * @throws DukeInvalidDateFormatException If the date is not properly formatted.
     */

    public Event(String description, String atSchedule) throws DukeInvalidDateFormatException {
        super(description, "E");
        this.atSchedule = TaskSchedule.parseSchedule(atSchedule);
    }

    /**
     * Returns the schedule of the event instance.
     * @return The date of the event.
     */

    public TaskSchedule getEventSchedule() {
        return this.atSchedule;
    }

    /**
     * Overrides the Object's toString method
     * and it contains the mark, the description, as well as
     * the schedule of the event.
     * @return The String that represents the event's details.
     */

    @Override
    public String toString() {
        assert atSchedule != null : "The event date should be entered to this task.";
        return String.format("[%s] [%s] %s (at: %s)", this.type, getStatusIcon(), this.description, this.atSchedule);
    }
}
