package duke.task;

import duke.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = DateTimeUtil.stringAsDateTime(deadline);
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toSaveString() {
        //D0Fnish project@June 6
        return "D" + (isDone ? "1" : "0") + name + "@" + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy h.mma")) + ")";
    }
}
