import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate date;

    /**
     * Constructor method for the Event Task.
     * @param description Description of the Event Task.
     * @param date Date on which the Event is occurring.
     */
    public Event(String description, LocalDate date) {
        super(description, "E");
        this.date = date;
    }

    /**
     * Getter method to return the date of the Event Task as a STRING object in the format dd-MM-yyyy.
     * @return Date of the Event Task.
     */
    @Override
    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = this.date.format(formatter);
        return formattedDate;
    }

    /**
     * Getter method to return the date of the Event Task as a String object in the format yyyy-MM-dd.
     * @return Returns date as a String in the yyyy-MM-dd format.
     */
    public String getDateForWritingToFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = this.date.format(formatter);
        return formattedDate;
    }

}