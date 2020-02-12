import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected String at;
    protected LocalDate ld;

    //at is given in "d-MM-yyyy time" format
    public Event(String description, String at) {
        super(description);
        ld = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + ld.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
