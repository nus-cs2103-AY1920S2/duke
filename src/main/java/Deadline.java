import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline handles deadline task for Duke.
 */
public class Deadline extends Task {
    protected String by;
    String dateAsString = "";
    LocalDate date;
    String statement = "";

    /**
     * Constructs a Deadline object for task added with deadline type.
     * @param description Description of deadline task.
     * @param by Due date/string.
     * @throws DateTimeParseException Parse the date if the given format is correct, else will parse the string.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = by;
        String[] tmp = this.by.split(" ");
        statement = tmp[0];
        dateAsString = dateAsString + tmp[1];

        try {
            date = LocalDate.parse(dateAsString);
        } catch (DateTimeParseException e) {

        }
    }

    /**
     * Formats the task details into text file for saving and loading.
     * @return Details of the task.
     */
    @Override
    public String format() {
        return "D " + super.getStatusInNumber() + " " + super.description + " /" + by;
    }

    /**
     * Returns the task details for Duke to print.
     * @return Task details.
     * @throws NullPointerException Prints the string of deadline details rather than the converted date format.
     */
    @Override
    public String toString() throws NullPointerException {
        String str = "[D]" + super.toString() + " (" + statement + " ";
        try {
            str = str + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } catch (NullPointerException e) {
            str = "[D]" + super.toString() + " (" + by + ")";
        }
        return str;
    }
}