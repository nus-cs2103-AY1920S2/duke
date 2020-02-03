package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate dueDate;

    public Deadline(String desc, LocalDate dueDate) {
        super(desc);
        this.dueDate = dueDate;
    }

    public Deadline(String desc, LocalDate dueDate, boolean isDone) {
        super(desc);
        super.setStatus(isDone);
        this.dueDate = dueDate;
    }

    @Override
    public String generateSaveFileEntry() {
        return String.format("D | %d | %s | %s\n", this.getStatusAsInt(), this.getDescription(),
                this.dueDate.toString());
    }

    @Override
    public String toString() {
        return String.format("[D] [%s] %s (by: %s)", this.getStatus(), this.getDescription(),
                this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}