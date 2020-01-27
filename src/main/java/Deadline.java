import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    protected String by;
    String dateAsString = "";
    LocalDate d;
    String statement = "";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        String[] tmp = this.by.split(" ");
        statement = tmp[0];
        dateAsString = dateAsString + tmp[1];
        d = LocalDate.parse(dateAsString);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + statement + " " + d.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}