import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;
    protected LocalDate formattedDate;

    /**
     * Constructor for Event class.
     *
     * @param description Description for event.
     * @param at          Date of event.
     * @throws DukeException If date/description not specified.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        if (at.equalsIgnoreCase("")) {
            throw new DukeException("No date");
        }
        this.at = at;
        LocalDate eventDate = LocalDate.parse(at);
        this.formattedDate = eventDate;
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString() 
                + " (at: " 
                + formattedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) 
                + ")";
    }


    /**
     * Same as toString.
     *
     * @return Event toString.
     */
    public String saveToList() {
        return "[E]" 
                + super.toString() 
                + " (at: " 
                + formattedDate 
                + ")";
    }
}