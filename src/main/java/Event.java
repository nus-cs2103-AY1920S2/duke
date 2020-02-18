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

    Event(String name, LocalDate date, boolean isDone) {
        super(name, isDone);
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
    public String tobeReplaced() {
        String temp = "   [E][" + 1 + "] " + super.getName() + " (at: " + date + ")\n";
        return temp;
    }

    /**
     * Returns the string corresponds to the current item.
     */
    public String currentString() {
        String temp =  "   [E][" + 0 + "] " + super.getName() + " (by: " + date + ")\n";
        return temp;
    }

    public LocalDate getDate() {
        return this.date;
    }
}
