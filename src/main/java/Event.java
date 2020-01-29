import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public Event(String msg, LocalDate date) {
        super(msg);
        super.type = "E";
        super.time = date;
    }

    @Override
    public String writeToFile() {
        return "E , " + super.status + " ," + super.msg + " , " + super.time;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.status + "]" + super.msg + " (by: "
                + super.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
