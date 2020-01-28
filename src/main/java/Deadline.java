import java.time.LocalDate;

public class Deadline extends Item {
    LocalDate date;
    boolean done;
    Deadline(String name, LocalDate date) {
        super(name);
        this.date = date;
        this.done = false;
    }

    Deadline(String name, LocalDate date, boolean done) {
        super(name, done);
        this.date = date;
    }

    public String toString() {
        String temp = "   [D]" + super.toString() + " (by: "+ date + ")\n";
        return temp;
    }
    public String replace() {
        String temp = "   [D][✗] " + super.getName() + " (by: "+ date + ")\n";
        return temp;
    }
}
