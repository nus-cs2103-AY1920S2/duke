package duke.pack;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String time;
    protected LocalDate date;

    /**
     * creates a deadline type of task
     * @param description the task to be done
     * @param time the time the task is due
     * @param date the date the task is due
     */
    public Deadline(String description, String time, LocalDate date) {
        super(description);
        this.time = time;
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +  " " + time + ")";
    }

}
