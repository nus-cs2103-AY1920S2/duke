import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * CS2103 Individual Project.
 * The Deadline class extends the Task class, it encapsulates all the information pertaining
 * to deadline objects, and contains a deadline which is a String.
 * @author Chiang Shun De
 */
public class Deadline extends Task {
    private String dateInput;
    private LocalDate date;
    private boolean hasTime;
    private String timeInput;

    /**
     * The constructor method for the Deadline class and formats the date input.
     * @param description A String description of the Deadline.
     * @param dateInput A String description of the date of Deadline.
     */
    public Deadline(String description, String dateInput) {
        super(description);
        this.hasTime = false;
        this.dateInput = dateInput;
        if (dateInput.split(" ").length > 1) {
            this.dateInput = dateInput.split(" ")[0];
            this.timeInput = dateInput.split(" ")[1];
            this.hasTime = true;
        }
            this.date = LocalDate.parse(this.dateInput);
    }

    /**
     * The constructor method for the Deadline class and formats the date and time input.
     * @param description A String description of the Deadline.
     * @param dateInput A String description of the date of Deadline.
     * @param timeInput A String description of the time of Deadline.
     */
    public Deadline(String description, String dateInput, String timeInput) {
        super(description);
        this.dateInput = dateInput;
        this.timeInput = timeInput;
        this.date = LocalDate.parse(dateInput);
        this.hasTime = true;
    }

    /**
     * Saves the deadline object in a human readable string format with all
     * the important information.
     * @return A String containing information of the deadline saved in a
     * human readable format.
     */
    @Override
    public String saveTask() {
        if (!hasTime) {
            return "D | " + this.binary + " | " + this.description + " | " + this.dateInput;
        } else {
            return "D | " + this.binary + " | " + this.description + " | " + this.dateInput + " " + this.timeInput;
        }
    }

    /**
     * Returns a string representation of the Deadline object with the date and time formatted.
     * @return A String representation of the Deadline object with the proper date and time format.
     */
    @Override
    public String toString() {
        if (!hasTime) {
            return "     [D]" + this.getIcon() + " " + this.description + " (by: "
                    + this.date.getDayOfWeek().toString().substring(0,3)  + " "
                    + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "     [D]" + this.getIcon() + " " + this.description + " (by: "
                    + this.date.getDayOfWeek().toString().substring(0,3)  + " "
                    + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.timeInput + "hrs)";
        }
    }
}
