import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate date;

    /**
     * Constructor method for a Deadline Task
     * @param description Description of the Deadline Task
     * @param date Date by which you wish the Deadline Task to be completed by
     */
    public Deadline(String description, LocalDate date) {
        super(description, "D");
        this.date = date;
    }

    /**
     * Getter method to get the date for the Deadline Task as a STRING object.
     * @return Date for the Deadline Task.
     */
    @Override
    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = this.date.format(formatter);
        return formattedDate;
    }

    /**
     * Getter method to return the date of the Deadline Task as a String object in the format yyyy-MM-dd.
     * @return Formatted date as a string in the yyyy-MM-dd format
     */
    public String getDateForWritingToFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = this.date.format(formatter);
        return formattedDate;
    }

}