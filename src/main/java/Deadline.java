import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;
    protected LocalTime time;

    public Deadline(boolean isDone, String description, String byString) {
        super(isDone, description);

        //Date input format: yyyy-mm-dd HHmm
        String[] by = byString.split(" ");
        this.date = LocalDate.parse(by[0]);
        if (by.length > 1) {
            this.time = LocalTime.parse(by[1], DateTimeFormatter.ofPattern("HHmm"));
        } else {
            this.time = null;
        }
    }

    @Override
    public String toString() {
        String dateTime = "";
        dateTime += this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        if (this.time != null) {
            dateTime += " " + this.time.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }
}