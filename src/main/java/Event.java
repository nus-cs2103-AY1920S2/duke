import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate date;
    protected LocalTime time;

    public Event(boolean isDone, String description, String atString) {
        super(isDone, description);

        //Date input format: yyyy-mm-dd HHmm
        String[] at = atString.split(" ");
        this.date = LocalDate.parse(at[0]);
        if (at.length > 1) {
            this.time = LocalTime.parse(at[1], DateTimeFormatter.ofPattern("HHmm"));
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
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}