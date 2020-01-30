import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Represents a Deadline task
 */
public class Deadline extends Task {

    /** Date the deadline is due by*/
    protected LocalDate date;

    /**
     * Creates a Deadline object
     * @param description What type of task is due by the deadline
     * @@param date when the task is due by
     */
    public Deadline (String description, LocalDate date) {
        super (description);
        this.date = date;
    }
    /**
     * Formats the deadline task before it is saved into hard disk
     *
     * @return a string that is formatted to be saved into hard disk
     */
    @Override
    public String saveFile() {
        if (this.status.equals("Done")) {
            return  "D|1||" + this.description + "|||" + this.date;
        } else {
            return  "D|0||" + this.description + "|||" + this.date;
        }
    }
    /**
     * Converts deadline object into string format to describe the task
     *
     * @return A string that describes the task, what type of task and when it is due
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
