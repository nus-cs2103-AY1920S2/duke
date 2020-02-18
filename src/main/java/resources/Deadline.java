package resources;

import exceptions.DukeException;

import java.time.LocalDateTime;

/**
 * Deadlines is a type of task that accepts /by statements only
 */
public class Deadline extends Task {

    private LocalDateTime deadline;

    /**
     * Instantiates a new Resources.Deadline.
     *
     * @param description the description
     * @throws DukeException the duke exception
     */
    public Deadline(String description) throws DukeException {
        super("default");

        String[] parsed = getParse(description);
        deadline = DateTimeHandler.getLocalDateTime(parsed[1]);
        super.resetDesc(parsed[0]);
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    private String[] getParse(String desc) throws DukeException {
        if (!desc.contains("/by ")) {
            throw new DukeException("Hello? Missing your /by command!");
        }
        String[] parsed = desc.split("/by ");
        return parsed;
    }
    @Override
    public String toString() {
        return "[D] " + super.toString()
                + " (by: " + DateTimeHandler.printsLocalDateTime(deadline)
                + ")";
    }
}
