import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    String at;
    String parsedAt;

    public Event(String[] description) {
        super(description[0]);
        at = (description[1].split(" ", 2))[1];
        modifyDateFormat();
    }

    public void modifyDateFormat() {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime parsedDate = LocalDateTime.parse(this.at, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        this.parsedAt = parsedDate.format(outputFormatter);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "(at: " + parsedAt + ")";
    }
}
