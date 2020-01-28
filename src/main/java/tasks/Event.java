package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalTime startAtTime;
    private LocalDate startAtDate;

    public Event(String description, String startAt) throws DateTimeException {
        super(description);

        String[] fields = startAt.split(" ");
        this.startAtTime = fields[0].equals("-")
                ? null
                : LocalTime.parse(fields[0]);

        this.startAtDate = fields[1].equals("-")
                ? LocalDate.now()
                : LocalDate.parse(fields[1]);
    }

    @Override
    public String toString() {
        String date = this.startAtDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String dateTime = this.startAtTime != null
                ? this.startAtTime + ", " + date
                : date;

        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }

    public LocalDate getDate() {
        return this.startAtDate;
    }

    @Override
    public String toSaveFormat() {
        char d = super.getIsDone() ? '1' : '0';
        return "E | " + d + " | " + super.getDescription() + " | " + this.startAt;
    }
}
