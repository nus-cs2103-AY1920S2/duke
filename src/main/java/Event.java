import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends AbstractTask {
    LocalDate date;
    String time;
    String preposition;

    public Event(String taskName, String preposition, LocalDate date) {
        super(taskName);
        this.preposition = preposition;
        this.date = date;
    }

    public Event(String taskName, String preposition, LocalDate date, String time) {
        super(taskName);
        this.preposition = preposition;
        this.date = date;
        this.time = time;
    }

    public String formattedDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return (this.time != null) ? "[E]" + taskStateString() + " " + this.taskName + " " + preposition + " " + formattedDate() + " " + this.time
                : "[E]" + taskStateString() + " " + this.taskName + " " + this.preposition + " " + formattedDate();
    }

}