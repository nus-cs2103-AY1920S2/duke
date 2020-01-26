import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    String time;
    LocalDate localDate;

    public Event(String description, String time) {//String time) {
        super(description);

        this.time = time;
        localDate = LocalDate.parse(time);
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
