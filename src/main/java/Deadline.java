import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    String by;
    LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
        totalTasks++;
    }

    public String getType() {
        return "[D]";
    }

    @Override
    public void taskSummary() {
        System.out.println(getType() + getStatusIcon() + " " + description+ "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }

    public String saveString() {
        return "D|" + (isDone? "1|" : "0|") + description + "|" + by;
    }
}
