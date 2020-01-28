package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String a) {
        super(description);
        this.by = LocalDate.parse(a);
    }

    public Deadline(String description, String a, boolean mark) {
        super(description, mark);
        this.by = LocalDate.parse(a);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by
                .format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String saveFormat() {
        return "D" + " , " + (super.isDone ? "1" : "0") + " , " + super.description + " , "
                + this.by;
    }
}