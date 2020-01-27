package dude;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate dueDate;

    public Deadline(String details, LocalDate dueDate, boolean isDone) {
        super(details, isDone);
        this.dueDate = dueDate;
    }

    @Override
    public boolean occursOn(LocalDate date) {
        return date.isEqual(this.dueDate);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                dueDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }

    @Override
    public String storeFormat() {
        return String.format("D|%s|%s|%s", getStatusIcon(), getDetails(), this.dueDate);
    }
}