package liaomeng.duke;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(boolean isDone, String description, LocalDate deadline, PriorityLevel level) {
        super(isDone, description, level);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "\n    (by: " + deadline + ", " + deadline.getDayOfWeek() + ")";
    }

    @Override
    public String toSimplerString() {
        return "D//" + super.toSimplerString() + "//" + deadline;
    }
}
