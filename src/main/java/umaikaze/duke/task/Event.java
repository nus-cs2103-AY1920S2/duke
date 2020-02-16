package umaikaze.duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate date;
    protected LocalTime time;

    public Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    @Override
    public String getSaveString() {
        return "E|" + (isDone ? 1 : 0) + "|" + description + "|"
                + date.format(DateTimeFormatter.ofPattern("d/M/yyyy"))
                + (time != null ? time.format(DateTimeFormatter.ofPattern(" H:mm)")) : ")");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + date.format(DateTimeFormatter.ofPattern("EEEE, d MMM yyyy"))
                + (time != null ? time.format(DateTimeFormatter.ofPattern(" H:mm)")) : ")");
    }
}
