package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public LocalDate time;

    public Event(String name, LocalDate time) {
        super(name);
        done = false;
        this.time = time;
        count++;

    }

    public Event(String name, LocalDate time, boolean done) {
        super(name);
        this.done = done;
        this.time = time;
        count++;

    }
    public String toString() {
        if (done) {
            return ("[E][✓] " + name + " | at: "+time.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+ "\n");
        }

        return ("[E][✗] " + name+ " | at: "+time.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+ "\n");
    }
}
