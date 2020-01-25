import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime datetime;

    public Event(String name, LocalDateTime datetime) {
        super(name);
        this.datetime = datetime;
    }

    public Event(String name, String datetime) {
        super(name);
        this.datetime = DateTimeUtil.stringAsDateTime(datetime);
    }

    public LocalDateTime getDatetime() {
        return datetime;
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
                " (at: " + datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy h.mma")) + ")";
    }
}
