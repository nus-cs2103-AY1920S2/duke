import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
    private final LocalDate deadline;

    Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    private Deadline(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public Deadline complete() {
        return new Deadline(description, deadline, true);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}
