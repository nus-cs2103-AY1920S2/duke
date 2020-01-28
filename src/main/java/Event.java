import java.time.LocalDate;
public class Event extends Item {
    LocalDate date;
    boolean done;
    Event(String name, LocalDate date) {
        super(name);
        this.date = date;
        this.done = false;
    }

    Event(String name, LocalDate date, boolean done) {
        super(name, done);
        this.date = date;
    }

    public String toString() {
        String temp = "   [E]" + super.toString() + " (at: "+ date + ")\n";
        return temp;
    }

    public String replace() {
        String temp = "   [E][✗] " + super.getName() + " (at: "+ date + ")\n";
        return temp;
    }
}
