package task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;
    protected LocalTime time;
    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
    static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");

    /**
     * Constructor for creating new Deadline object.
     *
     * @param description This is the description of the Deadline.
     * @param date This is the date the Deadline is on.
     * @param time This is the time the Deadline is on.
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * This method retrieves the date the Deadline is on.
     *
     * @return date the Deadline is on.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * This method retrieves deadline of Deadline.
     *
     * @return date and time the Deadline is on.
     */
    public String getDeadline() {
        return this.date + ", " + this.time;
    }

    /**
     * Returns the String representation of Deadline.
     *
     * @return String of the Deadline.
     */
    @Override
    public String toString() {
        String formattedDate = this.date.format(dateFormatter);
        String formattedTime = this.time.format(timeFormatter);
        return "[D][" + super.getStatusIcon() + "] " + super.toString() + " (by: " + formattedDate
                + ", " + formattedTime + ")";
    }
}