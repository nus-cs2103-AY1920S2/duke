package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import duke.task.Task;

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

    public Event(String name, boolean isDone, String atTime) {
        super(name, isDone);
        this.atTime = atTime;
        type = TaskType.event;
        convertDateTime(atTime);
    }

    /**
     * Puts important details into a String that is suitable for storing in files
     * @return a String in the save-file format
     */
    public String encoder() {
        return String.format("E:%s:%d:%s\n", super.name, (super.isDone ? 1 : 0), atTime);
    }

    public TaskType getTaskType() {
        return type;
    }

    /**
     * Stringify the object
     * @return a String representing the Object
     */
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
