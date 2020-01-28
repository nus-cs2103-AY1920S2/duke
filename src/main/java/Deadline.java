import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Item {
    LocalDate date;
    boolean done;
<<<<<<< HEAD
    Deadline(String name, String time) {
        super(name, false);
        this.time = time;
=======
    Deadline(String name, LocalDate date) {
        super(name, false);
        this.date = date;
>>>>>>> 2de8869436177ff5920973b07f499d4da26cf8a7
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
