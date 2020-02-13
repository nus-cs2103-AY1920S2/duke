package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A more detailed implementation of the task.Task class with specified date, start time and end time.
 */
public class Event extends Task {

    private LocalDate date;
    private String startTime;
    private String endTime;

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

    /**
     * Gets the start time of this event.
     *
     * @return the start time
     */
    public String getStartTime() {

        return startTime;
    }

    /**
     * Gets the end time of this event.
     *
     * @return the end time
     */
    public String getEndTime() {

        return endTime;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (!(object instanceof Event)) {
            return false;
        }

        Event event = (Event) object;
        return compareEvents(event);

    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                + startTime + "-" + endTime + ")";
    }

    /**
     * Compares this event with another
     *
     * @param event the event to compare with
     * @return boolean whether this event is the same as the parameter
     */
    private boolean compareEvents(Event event) {

        boolean hasSameDescription = (description.equals(event.description));
        boolean hasSameDate = (date.equals(event.date));
        boolean hasSameStartTime = (startTime.equals(event.startTime));
        boolean hasSameEndTime = (endTime.equals(event.endTime));

        return hasSameDate && hasSameDescription && hasSameStartTime && hasSameEndTime;
    }

}
