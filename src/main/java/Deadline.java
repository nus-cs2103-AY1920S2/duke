import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    //tasks that need to be done before a specific date/time
    // e.g., submit report by 11/10/2019 5pm

    protected String time;
    protected LocalDateTime ldt;
    private static TaskType type = TaskType.DEADLINE;

    public Deadline(String description, String time) {
        super(description, time);
        this.time = time;
        TimeParser tp = new TimeParser(time);
        ldt = tp.getTime();
    }

    public TaskType getType () {
        return type;
    }

    @Override
    public String toString () {
        return "[D]" + super.toString() + " (by: " + ldt.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";

    }
}
