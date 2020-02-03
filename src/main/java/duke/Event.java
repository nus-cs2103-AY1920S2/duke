package duke;

import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate date;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        String newdate = String.format("%d %s %d", this.date.getDayOfMonth(), this.date.getMonth(), this.date.getYear());
        return "[D]" + super.toString() + " (at: " + newdate + ")";
    }

    @Override
    public String toFileFormat() {
        return String.format("%s | %d | %s | %s", "D", this.isDone ? 1 : 0, this.description, convertDateToString(this.date));
    }
}
