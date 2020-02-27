package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * This Event class extends to Task class
 */
public class Event extends Task {
    protected LocalDate when;
    protected String details;

    public Event(String description, String date){
        super(description);
        this.details = date;
        this.when = LocalDate.parse(date);
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + extraInfo() + ")";
    }

    /**
     * This method is to the paremeter "when." It will override the parent's class of the same method.
     * @return a formated string, concat withe paremeter "when"
     */
    @Override
    public LocalDate getWhen(){
        return when;
    }
    /**
     * This method is to return the paremeter "when." It will override the parent's class of the same method.
     * @return when
     */
    public String extraInfo(){
        return this.when.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    public String getStringDate(){
        return details;
    }
}
