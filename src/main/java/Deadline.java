import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate dueDate;

    public Deadline(String desc, LocalDate dueDate) {
        super(desc);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format("[D] [%s] %s (by: %s)", this.getStatus(), this.description,
                this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
