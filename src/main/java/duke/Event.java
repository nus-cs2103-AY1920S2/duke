package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate time;

    /***
     * Constructor of newly created event
     * @param name
     * @param time
     */
    public Event(String name, LocalDate time) {
        super(name);
        done = false;
        this.time = time;
    }

    /***
     * Constructor of newly read event
     * @param name
     * @param time
     * @param done
     */
    public Event(String name, LocalDate time, boolean done) {
        super(name);
        this.done = done;
        this.time = time;
    }

    /***
     * Specify print format
     * @return print format
     */
    public String toString() {
        if (done) {
            return ("[E][v] " + name + " | at: "+time.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+ "\n");
        }
        return ("[E][x] " + name+ " | at: "+time.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+ "\n");
    }
}
