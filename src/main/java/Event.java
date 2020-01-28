import java.time.LocalDate;

public class Event extends DateTimeTask {

    public Event(String description, LocalDate at) {
        super(description, at);
    }

    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDateTime() + ")";
    }
}
