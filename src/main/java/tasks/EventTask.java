package tasks;

import exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private String eventTime;
    private LocalDateTime parsedStartTime;
    private LocalDateTime parsedEndTime;

    public EventTask(String description, String eventTime) throws DukeException {
        super(description);
        this.eventTime = eventTime;
        parseEventTime(eventTime);
    }

    private void parseEventTime(String eventTime) throws DukeException {
        try {
            String[] eventTimeArray = eventTime.split(" to ");

            parsedStartTime = LocalDateTime.parse(eventTimeArray[0], DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            parsedEndTime = LocalDateTime.parse(eventTimeArray[1],DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (Exception e) {
            throw new DukeException("You've entered the event date incorrectly! Please use \"dd/mm/yyyy HHmm to " +
                    "dd/mm/yyyy/HHmm\" for your dates, e.g. 05/12/2020 1800 to 06/12/2020 1800");
        }
    }

    public LocalDateTime getParsedStartTime() {
        return parsedStartTime;
    }

    public LocalDateTime getParsedEndTime() {
        return parsedEndTime;
    }

    public String getEventTime() {
        return eventTime;
    }

    @Override
    public String toString() {
        return String.format("%s%s (at: %s to %s)", "[E]", super.toString(), parsedStartTime.format(DateTimeFormatter.ofPattern(
                "MMM d yyyy, h:mm a")), parsedEndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")));
    }
}
