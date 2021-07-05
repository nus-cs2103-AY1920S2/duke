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
        isDone = false;
        this.time = time;
    }

    /***
     * Constructor of newly read event
     * @param name
     * @param time
     * @param isDone
     */
    public Event(String name, LocalDate time, boolean isDone) {
        super(name);
        this.isDone = isDone;
        this.time = time;
    }

    /***
     * Specify print format
     * @return print format
     */
    public String toString() {
        if (isDone) {
            return ("[E][v] " + name + " | at: "+time.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+ "\n");
        }
        return ("[E][x] " + name+ " | at: "+time.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+ "\n");
    }

    public LocalDate getTime() {
        return time;
    }
}
