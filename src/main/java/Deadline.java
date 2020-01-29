import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate dateBy;
    DateTimeFormatter formattedOutput = DateTimeFormatter.ofPattern("MMM d yyyy");
    DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    Deadline(String taskName, LocalDate dateBy) {
        super(taskName);
        this.dateBy = dateBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + dateBy.format(formattedOutput) + ")";
}
