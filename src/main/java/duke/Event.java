package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    //tasks that start at a specific time and ends at a specific time
    // e.g., team project meeting on 2/10/2019 2-4pm
    protected String time;
    protected LocalDateTime ldt;
    private TaskType type = TaskType.EVENT;

    public Event(String description, String time) {
        super(description, time);
        this.time = time;
        TimeParser tp = new TimeParser(time);
        ldt = tp.getTime();
    }

    public TaskType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + ldt.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }

}
