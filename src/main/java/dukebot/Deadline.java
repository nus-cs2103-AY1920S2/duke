package dukebot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    protected Deadline(String description, LocalDateTime dateTime) {
        super(description, TaskType.DEADLINE, dateTime);
    }

    //    public String getTime() {
    //        return (this.time);
    //    }

    @Override
    public String toString() {
        return (this.description + " (by: " + this.dateTimeToString() + ")");
    }
}
