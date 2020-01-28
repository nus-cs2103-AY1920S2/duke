import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    //protected String at = null;
    protected LocalDateTime at = null;

    public Event(String description, String at, DateTimeFormatter format) {
        super(description);
        //this.at = at;
        this.at = LocalDateTime.parse(at, format);
    }

    //public Event(String description, String at, String retrieved) {
    //    super(description);
        //this.at = at;
    //    this.at = LocalDateTime.parse(at, formatter);
    //}

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }

    @Override
    public String fileString() {
        return "E | " + this.getStatusIcon() + " | " + description + " | " + at.format(formatter);
    }
}