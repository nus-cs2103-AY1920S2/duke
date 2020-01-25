import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate datetime;

    public Event(String name, String datetime) {
        super(name);
        this.datetime = LocalDate.parse(datetime);
    }

    @Override
    public String toSaveString() {
        //E1Anna's Birthday@May 15
        return "E" +
                (isDone ? "1" : "0") +
                name + "@" +
                datetime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (at: " + datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
