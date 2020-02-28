package jiachen.duke;

import java.time.LocalDateTime;

/**
 * The type Deadline task.
 */
public class DeadlineTask extends Task {

    private String toBeDoneBy;

    /**
     * Instantiates a new Deadline task.
     *
     * @param description the description
     * @param deadline    the deadline
     * @throws InvalidDukeFormatException the invalid duke format exception
     */
    public DeadlineTask(String description, String deadline) throws InvalidDukeFormatException {
        super(description);
        this.toBeDoneBy = deadline;

        if (deadline.isEmpty()) {
            throw new InvalidDukeFormatException("Missing /by clause or missing by when!");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.toBeDoneBy + ")";
    }

    @Override
    public String format() {
        LocalDateTime dateString = LocalDateTime.parse(this.toBeDoneBy, DateTimeUtil.inputFormatter);
        return "D | " + super.format() + " | " + dateString;
    }
}
