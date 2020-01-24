package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class TimedTask extends Task {
    protected LocalDate dateTime;

    public TimedTask(String name, LocalDate dateTime) {
        super(name);
        this.dateTime = dateTime;
    }
}