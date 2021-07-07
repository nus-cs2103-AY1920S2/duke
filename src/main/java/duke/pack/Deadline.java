package duke.pack;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a command to add a deadline type of task.
 */
public class Deadline extends Task {
    protected LocalTime time;
    protected LocalDate date;
    protected final String type = "D";

    /**
     * Creates a deadline type of task.
     * @param description the task to be done
     * @param time the time the task is due
     * @param date the date the task is due
     */
    public Deadline(String description, LocalTime time, LocalDate date,
                    String dateToCompare, String timeToCompare) {

        super(description, dateToCompare, timeToCompare);
        this.time = time;
        this.date = date;
    }

    /**
     * Formats the task to be saved in hard disk.
     * @return a string of the formatted task
     */
    @Override
    public String formatForFile() {
        String done;
        if (isDone) {
            done = "1";
        } else {
            done = "0";
        }

        return type + " | " + done + " | " + description
                + " | " + time.toString() + " | " + date.toString() + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +  ", " +
                time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) + ")";
    }

}
