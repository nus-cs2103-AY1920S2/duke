package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class TimedTask extends Task {
    private DateTimeFormatter dtf;
    
    public TimedTask(String name) {
        super(name);
        dtf = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
    }

    protected String formatTime(LocalDateTime dt) {
        return dtf.format(dt);
    }

    @Override
    abstract public String toString();
}