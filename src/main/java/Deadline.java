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
