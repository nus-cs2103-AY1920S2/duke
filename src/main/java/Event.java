import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {

    public String type;

    /**
     * Constructor for Event class
     * Takes in a description and by date which will
     * define the task and when the due date is
     *
     * @param description description of the event task
     * @param at date of event
     */

    public Event(String description, String at) {
        super(description);
        super.at = at;
        this.type = "event";
    }


    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {

        if (super.getStatus() == 0) {

                return "[E][✗]" + super.toString() + " (at: " + super.at.trim() + ")";

        } else {
                return "[E][✓]" + super.toString() + " (at: " + super.at.trim() + ")";

        }
    }
}