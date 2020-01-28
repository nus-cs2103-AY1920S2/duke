package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends DateTask {
    private LocalTime finishByTime;
    private LocalDate finishByDate;

    public Deadline(String description, String finishBy) {
        super(description);

        String[] fields = finishBy.split(" ");
        if (fields.length != 2) {
            throw new DateTimeException("Insufficient parameters for date/time");
        }

        this.finishByTime = fields[0].equals("-")
                ? null
                : LocalTime.parse(fields[0]);
        this.finishByDate = fields[1].equals("-")
                ? LocalDate.now()
                : LocalDate.parse(fields[1]);
    }

    @Override
    public String toString() {
        String date = this.finishByDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String dateTime = this.finishByTime != null
                ? this.finishByTime + ", " + date
                : date;

        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }

    @Override
    public LocalDate getDate() {
        return this.finishByDate;
    }

    @Override
    public String toSaveFormat() {
        char d = super.getIsDone() ? '1' : '0';

        String time = this.finishByTime == null ? "-" : this.finishByTime.toString();
        return "D | " + d + " | " + super.getDescription() + " | " + time
                + " " + this.finishByDate;
    }
}
