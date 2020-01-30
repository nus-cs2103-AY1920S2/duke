import java.time.LocalDate;

public class Event extends Task {
    String dateString;

    public Event(String name, String dateString) {
        super(name);
        this.dateString = dateString;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "(at: " + dateString + ")";
    }
}
