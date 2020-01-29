import java.util.*;

public class Deadline extends Task {
    protected String by;

    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    public String toLine() {
        int num;
        if (super.done) {
            num = 1;
        } else {
            num = 0;
        }
        return "D/" + num + "/" + super.task + "/" + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
