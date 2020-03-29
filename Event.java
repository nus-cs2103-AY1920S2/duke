/**
 * Represents an event task
 * @author kerwei
 * @version 1.0
 */
public class Event extends Task {


    public Event(String type, String description, String dateTime){
        super(type, description, dateTime);
    }

    Datetime formattedDateTime = new Datetime(dateTime);

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + formattedDateTime + ")";
    }
}
