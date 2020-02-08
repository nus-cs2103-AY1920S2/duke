import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    protected LocalDate formattedDate;

    /**
     * Constructor for Deadline class.
     *
     * @param description Description for Deadline.
     * @param by          Date of Deadline
     * @throws DukeException If date/description not specified.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (by.equalsIgnoreCase("")) {
            throw new DukeException("No date");
        }
        this.by = by;
        LocalDate deadlineDate = LocalDate.parse(by);
        this.formattedDate = deadlineDate;
    }

    @Override
    public String toString() {
        return "[D]" 
                + super.toString() 
                + " (by: "
                + formattedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) 
                + ")";
    }

    /**
     * Same as toString.
     *
     * @return Deadline toString.
     */
    public String saveToList() {
        return "[D]" 
                + super.toString() 
                + " (by: " 
                + formattedDate 
                + ")";
    }
}