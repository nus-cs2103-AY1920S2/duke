import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String name, String deadline) {
        super(name);
        // "2019-12-01" Assume user always puts correct format
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toSaveString() {
        //D0Fnish project@June 6
        return "D" +
                (isDone ? "1" : "0") +
                name + "@" +
                deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
