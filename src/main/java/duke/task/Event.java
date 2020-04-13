package duke.task;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task that extends the TaskDate class. An Event object is represented by a String description,
 * boolean isDone, LocalDate date and LocalTime time, if a time is specified.
 */
public class Event extends TaskDate {

    public boolean isTime;

    /**
     * Constructs an Event object with a description, date and isDone boolean.
     *
     * @param description description of event
     * @param date        date of event
     * @param isDone      boolean of whether this event task is done
     */
    public Event(String description, LocalDate date, boolean isDone) {
        super(description, isDone, date);
        this.date = date;
        isTime = false;
    }

    /**
     * Constructs an Event object with a description, date, time and isDone boolean.
     *
     * @param description description of event
     * @param date        date of event
     * @param time        time of event
     * @param isDone      boolean of whether this event task is done
     */
    public Event(String description, LocalDate date, LocalTime time, boolean isDone) {
        super(description, isDone, date, time);
        this.date = date;
        this.time = time;
        isTime = true;
    }

    /**
     * Returns the LocalDate of the Event task.
     *
     * @return LocalDate of Event task
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns the LocalTime of the Event task.
     *
     * @return LocalTime of Event task
     */
    public LocalTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        String formattedDate = (this.date).format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        if (isTime) {
            String formattedTime = (this.time).format(DateTimeFormatter.ofPattern("h:mma"));
            return "[E]" + super.toString() + " (at:" + formattedDate + " " + formattedTime + ")";
        } else {
            return "[E]" + super.toString() + " (at:" + formattedDate + ")";
        }
    }
}

