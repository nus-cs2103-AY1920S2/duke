package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime date;

    public Event(String name, LocalDateTime date) {
        super(name);
        this.date = date;
    }

    public Event(String name, LocalDateTime date, boolean isDone) {
        super(name, isDone);
        this.date = date;
    }

    public String toString() {
        return "[E]" + super.toString() + "(at: " +
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:ss a")) + ")";
    }

    public String writeDrive() {
        return "E|" + (super.isDone() ? "1|" : "0|") + this.name + "|" +
                date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHss"));
    }

    public Event setDone() {
        return new Event(this.name, this.date, true);
    }
}
