package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import duke.util.DateTimeUtil;

/**
 * Represents an event with a date, a start time and an end time.
 */
public class Event extends Task {
    /** The date of this event. */
    protected final LocalDate at;
    /** The start of this event. */
    protected final LocalTime startTime;
    /** The end time of this event. */
    protected final LocalTime endTime;

    /**
     * Constructs a new event with a date, start time and end time.
     *
     * @param description the details of the deadline
     * @param at the date of the event.
     * @param startTime the start time of the event.
     * @param endTime the end time of the event.
     */
    public Event(String description, LocalDate at, LocalTime startTime, LocalTime endTime) {
        this(description, false, at, startTime, endTime);
    }

    private Event(String description, boolean isDone, LocalDate at,
                  LocalTime startTime,LocalTime endTime) {
        super(description, isDone);
        this.at = at;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public Event markDone() {
        return new Event(description, true, at, startTime, endTime);
    }

    @Override
    public String serialize() {
        return TaskSerializer.serialize(this,
                "E",
                at.format(DateTimeUtil.FORMAT_DATE_NUMERIC),
                startTime.format(DateTimeUtil.FORMAT_TIME_24H),
                endTime.format(DateTimeUtil.FORMAT_TIME_24H));
    }

    @Override
    public String toString() {
        // Date format is MMM d yyyy
        String formatDate = at.format(DateTimeUtil.FORMAT_DATE_MONTHNAME);

        String formatStartTime = startTime.format(DateTimeUtil.FORMAT_TIME_24H);
        String formatEndTime = endTime.format(DateTimeUtil.FORMAT_TIME_24H);

        return String.format("[E]%s (at: %s %s-%s)",
                super.toString(),
                formatDate,
                formatStartTime,
                formatEndTime);
    }

    @Override
    public String toFormatString() {
        // Date format is MMM d yyyy
        String formatDate = at.format(DateTimeUtil.FORMAT_DATE_MONTHNAME);

        String formatStartTime = startTime.format(DateTimeUtil.FORMAT_TIME_24H);
        String formatEndTime = endTime.format(DateTimeUtil.FORMAT_TIME_24H);

        return String.format("[E]%s\nat: %s\n    %s-%s",
                super.toString(),
                formatDate,
                formatStartTime,
                formatEndTime);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof Event) {
            Event event = (Event)obj;
            return this.at.equals(event.at)
                    && this.startTime.equals(event.startTime)
                    && this.endTime.equals(event.endTime);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), at, startTime, endTime);
    }
}
