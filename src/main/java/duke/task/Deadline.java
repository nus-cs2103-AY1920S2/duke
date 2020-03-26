package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate dateBy;

    public Deadline(String title, boolean isDone, LocalDate dateBy) {
        super(title, isDone);
        this.dateBy = dateBy;
    }

    public Deadline(String title, LocalDate dateBy) {
        this(title, false, dateBy);
    }

    public LocalDate getDateBy() {
        return dateBy;
    }

    @Override
    public Deadline setDone(boolean isDone) {
        return new Deadline(getTitle(), isDone, dateBy);
    }

    @Override
    public String toString() {
        return String.format(
                "[D][%s] %s (by: %s)",
                isDone() ? "\u2713" : "\u2717", // \u2713 = tick, \u2717 = cross
                getTitle(),
                dateBy.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Deadline)) {
            return false;
        }
        Deadline other = (Deadline) obj;
        return dateBy.equals(other.getDateBy()) && super.equals(obj);
    }
}
