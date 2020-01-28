import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Deadline extends Task {
    String due;
    String parsedDue;

    public Deadline(String[] description) {
        super(description[0]);
        due = (description[1].split(" ", 2))[1];
        modifyDateFormat();
    }

    public void modifyDateFormat() {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime parsedDate = LocalDateTime.parse(this.due, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        this.parsedDue = parsedDate.format(outputFormatter);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by: " + this.parsedDue + ")";
    }
}
