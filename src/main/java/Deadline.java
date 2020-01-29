import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final String bulletin = "[D]";

    private String byTime;
    private TaskType type;
    private LocalDate localDate;
    private LocalTime localTime;

    public Deadline(String name, String byTime) {
        super(name);
        this.byTime = byTime;
        type = TaskType.deadline;
        convertDateTime(byTime);
    }

    private void convertDateTime(String byTime) {
        localDate = LocalDate.parse(byTime.split(" ")[1]);
    }

    public Deadline(String name, boolean isDone, String byTime) {
        super(name, isDone);
        this.byTime = byTime;
        type = TaskType.deadline;
        convertDateTime(byTime);
    }

    public String encoder() {
        return String.format("D:%s:%d:%s\n", super.name, (super.isDone ? 1 : 0), byTime);
    }

    @Override
    public String toString() {
        if (super.isDone)
            return bulletin + " [" + doneSymbol + "] " + name + "by "
                    + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        else
            return bulletin + " [" + notDoneYetSymbol + "] " + name + "by "
                    + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
