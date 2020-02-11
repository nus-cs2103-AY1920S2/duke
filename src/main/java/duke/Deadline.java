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
        isDone = false;
        this.time = time;
    }

    /***
     * Constructor of newly read deadline
     * @param name
     * @param time
     * @param isDone
     */
    public Deadline(String name, LocalDate time, boolean isDone) {
        super(name);
        this.isDone = isDone;
        this.time = time;
    }

    public LocalDate getTime() {
        return time;
    }


    /***
     * Specify print format
     * @return print format
     */
    public String toString() {
        if (isDone) {
            return ("[D][v] " + name + " | by: "+time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n");
        }

        return ("[D][x] " + name+ " | by: "+time.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+ "\n");
    }
}
