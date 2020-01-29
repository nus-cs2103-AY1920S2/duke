import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime time;
    Event(String todo, String time) {
        super(todo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime myDateObj = LocalDateTime.parse(time, formatter);
        this.time = myDateObj;
    }

    @Override
    public String toString() {
        String timeStr = this.time.format(DateTimeFormatter.ofPattern("HH:mm, MMM d yyyy"));
        return String.format("[E]%s (at: %s)", super.toString(), timeStr);
    }
}