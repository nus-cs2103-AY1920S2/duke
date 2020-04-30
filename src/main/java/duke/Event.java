package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event Class extends the Task Class to store objects with a description with a time but no date attached.
 * @author qiujingying
 * @version 1.0
 */
public class Event extends Task {

    protected String time;
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    private TaskType type = TaskType.EVENT;

    /**
     * Creates an Event object with a description and specific start and end time.
     * @param description details of the Event
     * @param time start and end time
     */
    public Event(String description, String time) {
        super(description, time);
        this.time = time;
        String startTimeString = time.substring(0, 15);
        String endTimeString = time.substring(0, 11) + time.substring(16, 20);
        startTime = new TimeParser(startTimeString).getTime();
        endTime = new TimeParser(endTimeString).getTime();
    }

    /**
     * Returns the type of the Task.
     * @return TaskType.EVENT
     */
    public TaskType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + " till " + endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }

}
