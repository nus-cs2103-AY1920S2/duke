import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * This class deals with task with deadlines.
 */

public class Deadline extends Item {
    LocalDate date;
    boolean done;

    Deadline(String name, LocalDate date) {
        super(name, false);
        this.date = date;
    }

    Deadline(String name, LocalDate date, boolean done) {
        super(name, done);
        this.done = done;
        this.date = date;
    }

    public String toString() {
        String temp = "   [D]" + super.toString() + " (by: "+ date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")\n";
        return temp;
    }

    /**
     * This method returns the string before it is marked done, used for string substitution in the txt file when it is marked done.
     */
    public String replace() {
        String temp = "   [D][✗] " + super.getName() + " (by: "+ date + ")\n";
        return temp;
    }

    /**
     * This method returns the string corresponds to the current item.
     */
    public String now() {
        String temp = "   [D]" + super.toString() + " (by: "+ date + ")\n";
        return temp;
    }
}
