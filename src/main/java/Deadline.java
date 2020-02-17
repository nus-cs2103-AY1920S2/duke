import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Deadline is a child class of Task.
 * Task that needs to be done before a specific date/time.*/
public class Deadline extends Task {
    String dateString;
    LocalDate dateObj;

    /**
     * Constructor that takes in 2 params.
     * @param name Name of Deadline
     * @param dateString Date to finish by
     */
    public Deadline(String name, String dateString) {
        super(name);
        this.dateString = dateString;
        dateObj = LocalDate.parse(dateString);
    }

    public String getName() {
        return this.name;
    }

    public boolean getDone() {
        return this.done;
    }

    public String getClassName() {
        return "deadline";
    }

    public LocalDate getDateObj() {
        return this.dateObj;
    }

    /**
     * Converts Deadline into a String to be saved to file.
     * @return String to be saved to file
     */
    @Override
    public String toFile() {
        int doneInt = done ? 1 : 0;
        return "D , " + doneInt + " , " + name + " , " + dateString;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + dateObj.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
