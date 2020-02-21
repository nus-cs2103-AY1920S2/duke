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

    /**
     * Constructor with a name and atTime.
     * @param name
     * @param atTime
     */
    public Event(String name, String atTime) {
        super(name);
        this.atTime = atTime;
        type = TaskType.EVENT;
        convertDateTime(atTime);
    }

    private void convertDateTime(String atTime) {
        localDate = LocalDate.parse(atTime.split(" ")[1]);
    }

    /**
     * Constructor with a name, a boolean and atTime.
     * @param name
     * @param isDone
     * @param atTime
     */
    public Event(String name, boolean isDone, String atTime) {
        super(name, isDone);
        this.atTime = atTime;
        type = TaskType.EVENT;
        convertDateTime(atTime);
    }

    /**
     * Puts important details into a String that is suitable for storing in files.
     * @return a String in the save-file format.
     */
    public String encoder() {
        return String.format("E:%s:%d:%s\n", super.name, (super.isDone ? 1 : 0), atTime);
    }

    /**
     * Get the TaskType of the Task.
     * @return EVENT type.
     */
    public TaskType getTaskType() {
        return type;
    }

    /**
     * Stringify the object.
     * @return a String representing the Object.
     */
    @Override
    public String toString() {
        return String.format("%s [%s] %sby %s",
                bulletin, (super.isDone ? doneSymbol : notDoneYetSymbol), name,
                localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
