package jiachen.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event task takes in at at time clause.
 */
public class EventTask extends Task {

    private LocalDateTime at;

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

        if (at.isEmpty()) {
            throw new InvalidDukeFormatException("Missing /at clause or missing at when!");
        }

        this.at = LocalDateTime.parse(at, DateTimeUtil.inputFormatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeUtil.eventFormatter;
        return "[E]" + super.toString() + " (at: " + formatter.format(this.at) + ")";
    }

    @Override
    public String format() {
        return "E | "
            + super.format()
            + " | "
            + DateTimeUtil.inputFormatter.format(this.at);
    }
}
