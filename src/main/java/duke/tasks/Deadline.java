package duke.tasks;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime dateTime;

    public Deadline(String name, LocalDateTime dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatus(), name, dateTime);
    }
}