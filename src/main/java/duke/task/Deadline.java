package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task{
    /** Date the deadline task is due. */
    private LocalDate byDate = null;
    /** Time of the day the deadline task is due. */
    private LocalTime byTime = null;

    /**
     * Constructs a new Deadline task with description and date.
     * Task is set to not done by default.
     *
     * @param description description of the task.
     * @param byDateTime date and time the deadline task is due.
     */
    public Deadline(String description, String byDateTime) {
        super(description, false);
        String[] rawDateTime = byDateTime.split(" ");
        String[] date = rawDateTime[0].split("/");
        if (date[0].length() < 2) {
            date[0] = "0" + date[0];
        }
        if (date[1].length() < 2) {
            date[1] = "0" + date[1];
        }
        String formattedDate = date[2] + "-" + date[1] + "-"
                + date[0];
        String formattedTime = "" + rawDateTime[1].charAt(0)
                + rawDateTime[1].charAt(1) + ":" + rawDateTime[1].charAt(2)
                + rawDateTime[1].charAt(3) + ":00";
        this.byDate = LocalDate.parse(formattedDate);
        this.byTime = LocalTime.parse(formattedTime);
    }

    /**
     * Constructs a new Deadline task with description, date and done status.
     *
     * @param description description of the task.
     * @param isDone done status of the task.
     * @param byDateTime date and time the deadline task is due.
     */
    public Deadline(String description, boolean isDone, String byDateTime) {
        super(description, isDone);
        String[] rawDateTime = byDateTime.split(" ");
        String[] date = rawDateTime[0].split("/");
        if (date[0].length() < 2) {
            date[0] = "0" + date[0];
        }
        if (date[1].length() < 2) {
            date[1] = "0" + date[1];
        }
        String formattedDate = date[2] + "-" + date[1] + "-"
                + date[0];
        String formattedTime = "" + rawDateTime[1].charAt(0)
                + rawDateTime[1].charAt(1) + ":" + rawDateTime[1].charAt(2)
                + rawDateTime[1].charAt(3) + ":00";
        this.byDate = LocalDate.parse(formattedDate);
        this.byTime = LocalTime.parse(formattedTime);
    }

    /**
     * Gets the string representation of the deadline task used for saving.
     *
     * @return the string representation of the deadline task to be used for saving.
     */
    @Override
    public String getSaveRepresentation() {
        return "D|||" + getIsDone() + "|||" + getDescription() + "|||"
                + byDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy "))
                + byTime.format(DateTimeFormatter.ofPattern("kmm")) + "\n";
    }

    /**
     * Gets the string representation of the deadline task.
     *
     * @return the string representation of the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s, %s)", "D", (getIsDone() ? "\u2713" : "\u2718"), getDescription()
                , byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                , byTime.format(DateTimeFormatter.ofPattern("h:mma")));
    }
}