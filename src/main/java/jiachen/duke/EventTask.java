package jiachen.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Event task takes in at at time clause.
 */
public class EventTask extends Task {

    private String toBeDoneAt;

    /**
     * Instantiates a new Event task.
     *
     * @param description the description
     * @param at          the at
     * @throws InvalidDukeFormatException the invalid duke format exception
     * @throws DateTimeParseException     the date time parse exception
     */
    public EventTask(String description, String at)
        throws InvalidDukeFormatException, DateTimeParseException {
        super(description);
        this.toBeDoneAt = at;

        if (at.isEmpty()) {
            throw new InvalidDukeFormatException("Missing /at clause or missing at when!");
        }

        try {
            LocalDateTime.parse(this.toBeDoneAt, DateTimeUtil.INPUT_FORMATTER);
        } catch (Exception e) {
            throw new InvalidDukeFormatException("Invalid timestamp given!");
        }
    }

    @Override
    public String toString() {
        String formattedDateTime = LocalDateTime.parse(this.toBeDoneAt, DateTimeUtil.INPUT_FORMATTER)
            .format(DateTimeUtil.EVENT_FORMATTER);
        return "[E]" + super.toString() + " (at: " + formattedDateTime + ")";
    }

    @Override
    public String format() {
        return "E | "
            + super.format()
            + " | "
            + toBeDoneAt;
    }
}
