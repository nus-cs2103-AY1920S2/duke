import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public String date;
    public LocalDate localDate;

    public Event(String description) {
        super(description);
        String[] arr = description.split(" /at ");
        this.description = arr[0];
        this.date = arr[1];
        this.localDate = LocalDate.parse(arr[1]);
    }

    public Event(String description, int done) {
        super(description, done);
        String[] arr = description.split(" /at ");
        this.description = arr[0];
        this.date = arr[1];
        this.localDate = LocalDate.parse(arr[1]);
    }

    public String toPrint() {
        if (this.isDone) {
            return "E | " + 1 + " | " + this.description + " | " + this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return "E | " + 0 + " | " + this.description + " | " + this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    public String toString() {
        if (this.isDone) {
            return "[E][✓] " + this.description + " (at: " + this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[E][✗] " + this.description + " (at: " + this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }
}