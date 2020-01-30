package duke.pack;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String time;
    protected LocalDate date;
    protected String fullDesc;

    /**
     * creates a deadline type of task
     * @param description the task to be done
     * @param time the time the task is due
     * @param date the date the task is due
     * @param fullDesc the full description of task
     */
    public Deadline(String description, String time, LocalDate date, String fullDesc) {
        super(description, fullDesc);
        this.time = time;
        this.date = date;
    }

    /**
     * Formats the task to be saved in hard disk
     * @return a string of the formatted task
     */
    @Override
    public String formatForFile() {
        String type = "D";
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
        return "[D]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +  ", " + time + ")";
    }

}
