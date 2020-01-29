package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate time;

    /***
     * Constructor of newly created deadline
     * @param name
     * @param time
     */
    public Deadline(String name, LocalDate time) {
        super(name);
        done = false;
        this.time = time;
    }

    /***
     * Constructor of newly read deadline
     * @param name
     * @param time
     * @param done
     */
    public Deadline(String name, LocalDate time, boolean done) {
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
            return ("[D][✓] " + name + " | by: "+time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n");
        }

        return ("[D][✗] " + name+ " | by: "+time.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+ "\n");
    }
}
