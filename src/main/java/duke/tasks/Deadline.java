package duke.tasks;

import java.time.LocalDateTime;

public class Deadline extends TimedTask {
    protected LocalDateTime dateTime;

    public Deadline(String name, LocalDateTime dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    @Override
    public String toSaveable() {
        return String.format("deadline\n%s\n%s\n%s", name, dateTime, isDone);
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatus(), name, formatTime(dateTime));
    }
}