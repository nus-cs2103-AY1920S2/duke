import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public LocalDate time;

    public Deadline(String name, LocalDate time) {
        super(name);
        done = false;
        this.time = time;
        count++;

    }

    public Deadline(String name, LocalDate time, boolean done) {
        super(name);
        this.done = done;
        this.time = time;
        count++;

    }
    public String toString() {
        if (done) {
            return ("[D][✓] " + name + " | by: "+time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n");
        }

        return ("[D][✗] " + name+ " | by: "+time.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+ "\n");
    }
}
