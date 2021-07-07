package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Task object of type Event.
 */
public class Event extends Task {
    protected LocalDate atDate;
    protected LocalTime atTimeStart;
    protected LocalTime atTimeEnd;

    /**
     * Constructor for Event with description and deadline date.
     *
     * @param description Description of the event
     * @param atDate Event date
     */
    public Event(String description, LocalDate atDate) {
        super(description);
        this.atDate = atDate;
    }

    /**
     * Constructor for Event with description, event date, and event start time.
     *
     * @param description Description of the event
     * @param atDate Event date
     * @param atTimeStart Event start time
     */
    public Event(String description, LocalDate atDate, LocalTime atTimeStart) {
        super(description);
        this.atDate = atDate;
        this.atTimeStart = atTimeStart;
    }

    /**
     * Constructor for Event with description, event date, event start time, and event end time.
     *
     * @param description Description of the event
     * @param atDate Event date
     * @param atTimeStart Event start time
     * @param atTimeEnd Event end time
     */
    public Event(String description, LocalDate atDate, LocalTime atTimeStart, LocalTime atTimeEnd) {
        super(description);
        this.atDate = atDate;
        this.atTimeStart = atTimeStart;
        this.atTimeEnd = atTimeEnd;
    }

    /**
     * Stringifies the Event object to a default string representation.
     *
     * @return Default string representation of the Event object
     */
    @Override
    public String toString() {
        if (atTimeStart == null) {
            return "[E]" + super.toString() + " (at: "
                    + atDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
        } else {
            if (atTimeEnd == null) {
                return "[E]" + super.toString() + " (at: "
                        + atDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                        + ", " + atTimeStart.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
            } else {
                return "[E]" + super.toString() + " (at: "
                        + atDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                        + ", " + atTimeStart.format(DateTimeFormatter.ofPattern("HH:mm"))
                        + " to " + atTimeEnd.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
            }
        }
    }

    /**
     * Stringifies the Event object to a special string representation which includes the number of days to
     * the event (if event has not passed), or the number of days since the event (if event has passed).
     *
     * @return Formatted string representation of the Event object
     */
    public String displayEventTime() {
        try {
            if (atTimeStart == null) {
                return "[E]" + super.toString() + " (at: "
                        + atDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ") - "
                        + LocalDate.now().datesUntil(atDate).count() + " day(s) to go!";
            } else {
                if (atTimeEnd == null) {
                    return "[E]" + super.toString() + " (at: "
                            + atDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                            + ", " + atTimeStart.format(DateTimeFormatter.ofPattern("HH:mm")) + ") - "
                            + LocalDate.now().datesUntil(atDate).count() + " day(s) to go!";
                } else {
                    return "[E]" + super.toString() + " (at: "
                            + atDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                            + ", " + atTimeStart.format(DateTimeFormatter.ofPattern("HH:mm"))
                            + " to " + atTimeEnd.format(DateTimeFormatter.ofPattern("HH:mm")) + ") - "
                            + LocalDate.now().datesUntil(atDate).count() + " day(s) to go!";
                }
            }
        } catch (IllegalArgumentException iae) {
            if (atTimeStart == null) {
                return "[E]" + super.toString() + " (at: "
                        + atDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ") - "
                        + "it's " + atDate.datesUntil(LocalDate.now()).count() + " day(s) past the event!";
            } else {
                if (atTimeEnd == null) {
                    return "[E]" + super.toString() + " (at: "
                            + atDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                            + ", " + atTimeStart.format(DateTimeFormatter.ofPattern("HH:mm")) + ") - "
                            + "it's " + atDate.datesUntil(LocalDate.now()).count() + " day(s) past the event!";
                } else {
                    return "[E]" + super.toString() + " (at: "
                            + atDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                            + ", " + atTimeStart.format(DateTimeFormatter.ofPattern("HH:mm"))
                            + " to " + atTimeEnd.format(DateTimeFormatter.ofPattern("HH:mm")) + ") - "
                            + "it's " + atDate.datesUntil(LocalDate.now()).count() + " day(s) past the event!";
                }
            }
        }
    }
}
