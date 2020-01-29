package e0148811.duke;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ", " + deadline.getDayOfWeek() + ")";
    }
}
