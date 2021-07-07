package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Class Events wis for tasks which are Event format.
 */
public class Events extends Task {


    /**
     * The time when the event start.
     */
    protected LocalTime eventStart;

    /**
     * The time when the event end.
     */
    protected LocalTime eventEnd;

    /**
     * The Event date: the date the event held.
     */
    protected LocalDate eventDate;

    /**
     * Instantiates a new events.
     *
     * @param description the description
     * @param eventDate   the event date
     * @param eventStart  the starting time for the event
     * @param eventEnd    the end time for the event
     */
    public Events(String description, LocalDate eventDate, LocalTime eventStart, LocalTime eventEnd) {
        super(description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.eventDate = eventDate;
    }

    /**
     * Gets the event date.
     *
     * @return (LocalDate) eventDate
     */
    public LocalDate getEventDate() {
        return this.eventDate;
    }


    /**
     * Gets the eventStart.
     *
     * @return the eventStart timing e.g(2pm)
     */
    public LocalTime getEventStart() {
        return this.eventStart;
    }

    /**
     * Gets the eventEnd.
     *
     * @return the eventEnd timing e.g(2pm)
     */
    public LocalTime getEventEnd() {
        return this.eventEnd;
    }


    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        if (eventStart.compareTo(eventEnd) != -1) {
            return "[E]" + super.toString() + " (at: "
                    + eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy "))
                    + this.eventStart.format(DateTimeFormatter.ofPattern(" h.mm a")) + "  to "
                    + this.eventEnd.format(DateTimeFormatter.ofPattern(" h.mm a")) + "(next day) )\n";
        } else {
            return "[E]" + super.toString() + " (at: "
                    + eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy "))
                    + this.eventStart.format(DateTimeFormatter.ofPattern(" h.mm a")) + "  to "
                    + this.eventEnd.format(DateTimeFormatter.ofPattern(" h.mm a")) + ")\n";
        }
    }
}
