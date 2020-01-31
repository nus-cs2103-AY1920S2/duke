import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    String at;
    LocalDate date;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
        totalTasks++;
    }

    public String getType() {
        return "[E]";
    }

    @Override
    public void taskSummary() {
        System.out.println(getType() + getStatusIcon() + " " + description + "(at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }
}
