import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends AbstractTask {
    LocalDate date;
    String time;
    String preposition;

    public Deadline(String taskName, String preposition, LocalDate date) {
        super(taskName);
        this.preposition = preposition;
        this.date = date;
    }

    public Deadline(String taskName, String preposition, LocalDate date, String time) {
        super(taskName);
        this.preposition = preposition;
        this.date = date;
        this.time = time;
    }

    private String formattedDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return (this.time != null) ? "[D]" + taskStateString() + " " + this.taskName + " " + this.preposition + " " + formattedDate() + " " + this.time
                : "[D]" + taskStateString() + " " + this.taskName + " " + this.preposition + " " + formattedDate();
    }

}
