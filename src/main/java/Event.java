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

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");
        String formattedDate = this.date.format(dateFormatter);
        String formattedTime = this.time.format(timeFormatter);
        return "[E][" + super.getStatusIcon() + "] " + super.toString() + " (at: " + formattedDate
                + ", " + formattedTime + ")";
    }
}