package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A more detailed implementation of the task.Task class with specified date, start time and end time.
 */
public class Event extends Task {

    /**
     * The Date.
     */
    protected LocalDate date;
    /**
     * The Start time.
     */
    protected String startTime;
    /**
     * The End time.
     */
    protected String endTime;

    /**
     * Constructs a new instance of task.Event.
     *
     * @param description the description.
     * @param date        the date.
     * @param startTime   the start time.
     * @param endTime     the end time.
     */
    public Event(String description, LocalDate date, String startTime, String endTime) {

        super(description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Gets the date of this event.
     *
     * @return the date of this event.
     */
    public LocalDate getDate() {

        return date;
    }

    public String getStartTime() {

        return startTime;
    }

    public String getEndTime() {

        return endTime;
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                + startTime + "-" + endTime + ")";
    }

}
