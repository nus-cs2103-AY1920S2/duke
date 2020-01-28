import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate date;

    public Deadline(String command, String date) {
        super(command);
        this.date = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +  date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}