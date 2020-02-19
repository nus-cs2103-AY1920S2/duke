package Togepi.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Represents a task of type deadline.
 */
public class Deadline extends Task {
    private LocalDate by;

    /** Creates a task of type deaedline with
     * the specified description and end date.
     * @param description is the description of the task.
     * @param by is the date that the task needs to be completed by.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /** Produces string to be printed.
     * @return a string that represents the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /** Prints the string representation of the task that is to be saved in the file.
     * @return a string representation of the task to be saved in the file.
     */
    @Override
    public String saveToFile() {
        return "[D]" + super.saveToFile() + " - " + by;
    }
}