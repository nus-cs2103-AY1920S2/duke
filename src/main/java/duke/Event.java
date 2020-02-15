package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Event contains information about a given task with description and date of event.
 */
public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }
    
    private String getFormattedDate() {
        return this.at.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }
    
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)%s", super.toString(), this.getFormattedDate(), super.getTagsAsStr());
    }

    @Override
    protected String getFileFormattedLine() {
        return String.format("E|%s|%s|%s|%s",
                super.isDone ? "1" : "0", this.description, super.tags.toString(), this.at);
    }
}
