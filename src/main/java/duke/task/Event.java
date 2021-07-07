package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate processedTime = LocalDate.MIN;
    private boolean dateProcessed = true;
    private String time;

    /**
     * Constructor for Event class.
     *
     * @param description details regarding task
     * @param time        at which task has to be completed
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
        try {
            processedTime = LocalDate.parse(time);
        } catch (DateTimeException e) {
            dateProcessed = false;
        }
    }

    public String getTaskIcon() {
        return "E";
    }

    public boolean hasTime() {
        return true;
    }

    private String getTime() {
        return dateProcessed
                ? processedTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                : time;
    }


    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " + getTime() + ")";
    }
}