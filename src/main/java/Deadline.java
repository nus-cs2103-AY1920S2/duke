import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate date;

    public Deadline(String command, String date) {
        super(command);
        this.date = LocalDate.parse(date);
    }

    // e.g. D/Y/return book/June 5th
    @Override
    public String toStringTxt() {
        return "D/" + super.getIcon() + "/" + command + "/" + byDate + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +  date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}