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

    /**
     * Returns the string of the event item.
     */
    public String toString() {
        String temp = "   [E]" + super.toString() + " (at: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")\n";
        return temp;
    }

    /**
     * Returns the string before it is marked done, used for string substitution
     * in the txt file when it is marked done.
     */
    public String replace() {
        String temp = "   [E]["+ "\u2718" +"] "+ super.getName() + " (at: " + date + ")\n";
        return temp;
    }

    /**
     * Returns the string corresponds to the current item.
     */
    public String now() {
        String temp = "   [E]" + super.toString() + " (at: " + date + ")\n";
        return temp;
    }

    public LocalDate getDate() {
        return this.date;
    }
}
