import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task implements TimeParser {
    protected LocalDate deadline;

    Deadline(String unparsed) {
        String[] split = unparsed.split("/by");
        this.description = split[0].trim();
        this.deadline = TimeParser.parseDate(split[1].trim());
        super.TYPE = TaskType.DEADLINE;
    }

    Deadline(String status, String description, String deadline) {
        super(status, description);
        super.TYPE = TaskType.DEADLINE;
        this.deadline = TimeParser.parseDate(deadline);
    }

    public LocalDate getTaskTime() {
        return this.deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
