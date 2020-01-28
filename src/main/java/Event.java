import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Item {
<<<<<<< HEAD
    String time;

    Event(String name, String time) {
        super(name, false);
        this.time = time;
=======
    LocalDate date;
    boolean done;
    Event(String name, LocalDate date) {
        super(name, false);
        this.date = date;
>>>>>>> 2de8869436177ff5920973b07f499d4da26cf8a7
    }

    Event(String name, LocalDate date, boolean done) {
        super(name, done);
        this.date = date;
    }

    public String toString() {
        String temp = "   [E]" + super.toString() + " (at: "+ date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")\n";
        return temp;
    }

    public String replace() {
        String temp = "   [E][✗] " + super.getName() + " (at: "+ date + ")\n";
        return temp;
    }

    public String now() {
        String temp = "   [E]" + super.toString() + " (at: " + date + ")\n";
        return temp;
    }
}
