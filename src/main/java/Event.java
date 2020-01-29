import java.time.LocalDate;


public class Event extends Task {

    private String timing;

    public Event(String description, String timing) {
        super(description);
        int newTimingFormat = timing.indexOf(" ");
        this.timing = timing.substring(0, newTimingFormat) + ":"+ timing.substring(newTimingFormat);

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + this.timing + ")";

    }
}
