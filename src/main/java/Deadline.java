import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    String dateString;
    LocalDate dateObj;

    public Deadline(String name, String dateString) {
        super(name);
        this.dateString = dateString;
        dateObj = LocalDate.parse(dateString);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + dateObj.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
