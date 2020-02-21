package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * The Event class represents a task that start at a specific time and ends at a specific time e.g.,
 * team project meeting on 2/10/2019 2-4pm.
 */
public class Event extends Task {
    protected String fromTimeToTime;
    protected LocalDate date;

    /**
     * Create an event task from description, date and time provided.
     */
    public Event(String description, LocalDate date, String fromTimeToTime) {
        super(description);
        this.date = date;
        this.fromTimeToTime = fromTimeToTime;
    }

    /**
     * Get the date of the Event task.
     *
     * @return the date of the Event task
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Get the start and end times of the Event task.
     *
     * @return the start and end times of the Event task
     */
    public String getFromTimeToTime() {
        return this.fromTimeToTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " "
                + fromTimeToTime
                + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return Objects.equals(fromTimeToTime, event.fromTimeToTime)
                && Objects.equals(date, event.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromTimeToTime, date);
    }
}