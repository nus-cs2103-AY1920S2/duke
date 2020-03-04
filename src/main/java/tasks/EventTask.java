package tasks;

import exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event Task object, for events. Contains methods pertaining to an event task.
 */
public class EventTask extends Task {
    private String eventTime;
    private LocalDateTime parsedStartTime;
    private LocalDateTime parsedEndTime;

    /**
     * Constructor of an event task.
     *
     * @param description Description of the event.
     * @param eventTime   Period of which the event will run. Follows the format "dd/mm/yyyy HHmm to dd/mm/yyyy HHmm".
     * @throws DukeException For parseEventTime method.
     */
    public EventTask(String description, String eventTime) throws DukeException {
        super(description);
        this.eventTime = eventTime;
        parseEventTime(eventTime);
    }

    /**
     * Parses the event times and saves the start time and end time as two different LocalDateTime objects.
     *
     * @param eventTime Period of which the event will run. Follows the format "dd/mm/yyyy HHmm to dd/mm/yyyy HHmm".
     * @throws DukeException Throws an exception if the format is not followed.
     */
    private void parseEventTime(String eventTime) throws DukeException {
        try {
            String[] eventTimeArray = eventTime.split(" to ");

            parsedStartTime = LocalDateTime.parse(eventTimeArray[0], DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            parsedEndTime = LocalDateTime.parse(eventTimeArray[1], DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (Exception e) {
            throw new DukeException("You've entered the event date incorrectly! Please use \"dd/mm/yyyy HHmm to "
                    + "dd/mm/yyyy/HHmm\" for your dates, e.g. 05/12/2020 1800 to 06/12/2020 1800");
        }
    }

    /**
     * Returns the start time of the event, in LocalDateTime form.
     *
     * @return Object containing the start time of the event.
     */
    public LocalDateTime getParsedStartTime() {
        return parsedStartTime;
    }

    /**
     * Returns the end time of the event, in LocalDateTime form.
     *
     * @return Object containing the end time of the event.
     */
    public LocalDateTime getParsedEndTime() {
        return parsedEndTime;
    }

    /**
     * Returns the start and end time of the event in String form.
     *
     * @return String of the event time.
     */
    public String getEventTime() {
        return eventTime;
    }

    /**
     * Returns a String of the task with the type of task and date.
     *
     * @return Formatted String of a event task.
     */
    @Override
    public String toString() {
        return String.format("%s%s (at: %s to %s)", "[E]", super.toString(),
                parsedStartTime.format(DateTimeFormatter.ofPattern(
                "MMM d yyyy, h:mm a")), parsedEndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")));
    }
}
