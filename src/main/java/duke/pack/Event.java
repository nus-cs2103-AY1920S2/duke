package duke.pack;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String time;
    protected LocalDate date;

    /**
     * creates an event type of task
     * @param description task to be done
     * @param time time of event
     * @param date date of event
     */
    public Event(String description, String time, LocalDate date) {
        super(description);
        this.time = time;
        this.date = date;
    }

    @Override
    public String formatForFile() {
        String type = "E";
        String done;
        if (isDone) {
            done = "1";
        } else {
            done = "0";
        }

        return type + " | " + done + " | " + description
                + " | " + time + " | " + date.toString() + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", " + time + ")";
    }
}
