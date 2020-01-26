import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    //String time;
    LocalDate localDate;

    public Event(String description, LocalDate localDate) {//String time) {
        super(description);
        this.localDate = localDate;

        //this.time = time;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
