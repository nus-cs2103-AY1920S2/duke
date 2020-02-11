/**
 * Events is a type of task that can be stored in the task list
 */


package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Events extends Task {

    protected String at;
    protected LocalDateTime dateTime;

    public Events(String description, String at) {
        super(description);
        this.at = at;

        dateTime = LocalDateTime.parse(at);
    }

    public String getType() { return "E"; }

    @Override
    public String getDetails() { return at; }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                .format(dateTime) + ")";
    }
}