package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task that has some form of time information.
 */
public abstract class TimedTask extends Task {
    private DateTimeFormatter dtf;

    public TimedTask(String name) {
        super(name);
        dtf = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
    }

    protected String formatTime(LocalDateTime dt) {
        assert dtf != null : "DateTimeFormatter should be instantiated";
        return dtf.format(dt);
    }

}