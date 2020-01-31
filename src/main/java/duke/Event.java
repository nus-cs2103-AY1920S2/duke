package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate at;

    public Event(String name, LocalDate at) {
        super(name);
        this.at = at;
    }

    public Event(String name, LocalDate at, boolean isDone) {
        super(name, isDone);
        this.at = at;
    }

    public String getAt() {
        return at.toString();
    }

    @Override
    public String getMnemonic() {
        return "E";
    }

    @Override
    public String toString() {
        return "[" + getMnemonic() + "]" + super.toString()
                + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyy")) +  ")";
    }
}
