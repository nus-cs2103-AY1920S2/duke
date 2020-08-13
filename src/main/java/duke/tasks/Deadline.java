package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate date;
    protected String time;
    protected String rawDate;

    /**
     * Constructs new Deadline object.
     * @param taskName name of deadline.
     * @param dateTime date and time of deadline.
     */
    public Deadline(String taskName, String dateTime) {
        super(taskName);
        String[] dt = dateTime.split(" ");
        this.rawDate = dt[0];
        this.date = LocalDate.parse(getRawDate());
        this.time = dt[1];
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    public String getTime() {
        return this.time;
    }

    public String getDateTime() {
        return getDate() + " @ " + getTime();
    }

    public String getRawDate() {
        return rawDate;
    }

    public String getType() {
        return "[D]";
    }

    public String writeFormat() {
        return getType() + getStatus() + " " + getTaskName() + " /by " + getRawDate() + " " + getTime();
    }

    @Override
    public boolean equals(Object task) {
        if (task instanceof Deadline) {
            if (((Deadline) task).getTaskName().equals(this.getTaskName())) {
                return ((Deadline) task).getDateTime().equals(this.getDateTime());
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (due by: " + getDateTime() + ")";
    }
}
