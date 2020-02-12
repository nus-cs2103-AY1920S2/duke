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
    protected LocalDateTime ldt;
    private TaskType type = TaskType.EVENT;

    /**
     * Creates an Event object with a description and specific start and end time.
     * @param description details of the Event
     * @param time start and end time
     */
    public Event(String description, String time) {
        super(description, time);
        this.time = time;
        TimeParser tp = new TimeParser(time);
        ldt = tp.getTime();
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
        return "[E]" + super.toString() + " (at: " + ldt.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }

}
