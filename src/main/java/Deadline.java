import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    public String replace() {
        String temp = "   [D][✗] " + super.getName() + " (by: "+ date + ")\n";
        return temp;
    }

    public String now() {
        String temp = "   [D]" + super.toString() + " (by: "+ date + ")\n";
        return temp;
    }
}
