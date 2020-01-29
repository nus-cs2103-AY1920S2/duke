import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final String bulletin = "[E]";

    private String atTime;
    private TaskType type;
    private LocalDate localDate;
    private LocalTime localTime;

    public Event(String name, String atTime) {
        super(name);
        this.atTime = atTime;
        type = TaskType.event;
        convertDateTime(atTime);
    }

    private void convertDateTime(String atTime) {
        localDate = LocalDate.parse(atTime.split(" ")[1]);
    }

    @Override
    public String toString() {
        if (super.isDone)
            return bulletin + " [" + doneSymbol + "] " + name + "at "
                    + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        else
            return bulletin + " [" + notDoneYetSymbol + "] " + name + "at "
                    + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
