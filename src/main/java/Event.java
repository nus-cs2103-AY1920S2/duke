import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    String at;
    String parsedAt;

    /**
     * Creates event.
     * @param description contains description of event.
     */
    public Event(String[] description) {
        super(description[0]);
        at = (description[1].split(" ", 2))[1];
        modifyDateFormat();
    }

    /**
     * Changes format of Date.
     */
    public void modifyDateFormat() {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime parsedDate = LocalDateTime.parse(this.at, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        this.parsedAt = parsedDate.format(outputFormatter);
    }

    public Event(String description, String atDate, boolean isDone) {
        super(description, isDone);
        at = atDate;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + parsedAt + ")";
    }

    @Override
    public String addToFile() {
        return "E | " + super.addToFile() + " | " + parsedAt;
    }
}
