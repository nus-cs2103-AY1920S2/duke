import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * This class deals with task with deadlines.
 */

public class Deadline extends Item {
    LocalDate date;
    boolean isDone;

    Deadline(String name, LocalDate date) {
        super(name, false);
        this.date = date;
    }

    Deadline(String name, LocalDate date, boolean done) {
        super(name, done);
        this.isDone = done;
        this.date = date;
    }

    /**
     * Returns the string of the deadline item.
     */
    public String toString() {
        String temp = "   [D]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")\n";
        return temp;
    }

    /**
     * Returns the string before it is marked done,
     * used for string substitution in the txt file when it is marked done.
     */
    public String tobeReplaced() {
        String temp =  "   [D][" + 0 + "] " + super.getName() + " (by: " + date + ")\n";
        return temp;
    }

    /**
     * Returns the string corresponds to the current item marked as not done.
     */
    public String currentString() {
        String temp =  "   [D][" + 1 + "] " + super.getName() + " (by: " + date + ")\n";
        return temp;
    }

    /**
     * Returns the string corresponds to the current item.
     */
    public String checkString() {
        String temp = "   [D][";
        if (super.getDone()) {
            temp += "0";
        } else {
            temp += "1";
        }
        temp += "] " + super.getName() + " (by: " + date + ")\n";
        return temp;
    }

    public LocalDate getDate() {
        return this.date;
    }
}
