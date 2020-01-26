package duke.task;

import duke.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Deadline constructor with LocalDateTime for date.
     * @param name of the deadline task.
     * @param deadline of the task.
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Deadline constructor with String for date,
     * @param name of the deadline task.
     * @param deadline of the task.
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = DateTimeUtil.stringAsDateTime(deadline);
    }

    /**
     * Getter for a reference to the LocalDateTime of the deadline.
     * @return the LocalDateTime representation of the deadline.
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Gets the save-string representation of the task.
     * @return the String representation of the task Storage can save.
     */
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
                " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy h.mma")) + ")";
    }
}
