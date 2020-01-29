import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * This class deals with events with a specific date.
 */
public class Event extends Item {
    LocalDate date;
    Event(String name, LocalDate date) {
        super(name, false);
        this.date = date;
    }

    Event(String name, LocalDate date, boolean done) {
        super(name, done);
        this.date = date;
    }

    public String toString() {
        String temp = "   [E]" + super.toString() + " (at: "+ date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")\n";
        return temp;
    }

    /**
     * This method returns the string before it is marked done, used for string substitution in the txt file when it is marked done.
     */
    public String replace() {
        String temp = "   [E][✗] " + super.getName() + " (at: "+ date + ")\n";
        return temp;
    }

    /**
     * This method returns the string corresponds to the current item.
     */
    public String now() {
        String temp = "   [E]" + super.toString() + " (at: " + date + ")\n";
        return temp;
    }
}
