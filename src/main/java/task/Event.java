package task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate date;
    protected LocalTime time;
    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
    static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");

    /**
     * Constructor for creating new Event object.
     *
     * @param description This is the description of the Event.
     * @param date This is the date the Event is on.
     * @param time This is the time the Event is on.
     */
    public Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * This method retrieves the date the Event is on.
     *
     * @return date of the Event.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * This method retrieves date and time of Event.
     *
     * @return date and time the Event is on.
     */
    public String getEvent() {
        return this.date + ", " + this.time;
    }

    /**
     * Returns the String representation of Event.
     *
     * @return String of the Event.
     */
    @Override
    public String toString() {
        String formattedDate = this.date.format(dateFormatter);
        String formattedTime = this.time.format(timeFormatter);
        return "[E][" + super.getStatusIcon() + "] " + super.toString() + " (at: " + formattedDate
                + ", " + formattedTime + ")";
    }
}