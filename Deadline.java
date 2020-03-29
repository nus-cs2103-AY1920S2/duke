/**
 * Represents a deadline task
 * @author kerwei
 * @version 1.0
 */
public class Deadline extends Task {

    public Deadline(String type, String description, String dateTime){
        super(type, description, dateTime);
    }

    Datetime formattedDateTime = new Datetime(dateTime);

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + formattedDateTime + ")";
    }
}
