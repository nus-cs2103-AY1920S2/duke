import java.util.*;

public class Event extends Task {
    protected String at;

    public Event(String task, String at) {
        super(task);
        this.at = at;
    }

    public String toLine() {
        int num;
        if (super.done) {
            num = 1;
        } else {
            num = 0;
        }
        return "D/" + num + "/" + this.task + "/" + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}