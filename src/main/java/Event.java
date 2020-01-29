import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends TaskDate {

    protected LocalDate date;
    protected LocalTime time;
    protected boolean isTime;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
        isTime = false;
    }

    public Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
        isTime = true;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        String formattedDate = (this.date).format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        if (isTime) {
            String formattedTime = (this.time).format(DateTimeFormatter.ofPattern("h:mma"));
            return "[E]" + super.toString() + " (at:" + formattedDate + " " + formattedTime + ")";
        } else {
            return "[E]" + super.toString() + " (at:" + formattedDate + ")";
        }
    }
}

