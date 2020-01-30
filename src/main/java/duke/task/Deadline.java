package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final String bulletin = "[D]";

    private String byTime;
    private TaskType type;
    private LocalDate localDate;
    private LocalTime localTime;

    /**
     * Constructor with a name and byTime
     * @param name
     * @param byTime
     */
    public Deadline(String name, String byTime) {
        super(name);
        this.byTime = byTime;
        type = TaskType.deadline;
        convertDateTime(byTime);
    }

    private void convertDateTime(String byTime) {
        localDate = LocalDate.parse(byTime.split(" ")[1]);
    }

    /**
     * Constructor with a name, a boolean and byTime
     * @param name
     * @param isDone
     * @param byTime
     */
    public Deadline(String name, boolean isDone, String byTime) {
        super(name, isDone);
        this.byTime = byTime;
        type = TaskType.deadline;
        convertDateTime(byTime);
    }

    /**
     * Puts important details into a String that is suitable for storing in files
     * @return a String in the save-file format
     */
    public String encoder() {
        return String.format("D:%s:%d:%s\n", super.name, (super.isDone ? 1 : 0), byTime);
    }

    /**
     * Gets the TaskType of the Object
     * @return type of the object
     */
    public TaskType getTaskType() {
        return type;
    }

    /**
     * Stringify the object
     * @return a String representing the Object
     */
    @Override
    public String toString() {
        return String.format("%s [%s] %sby %s",
                bulletin, (super.isDone ? doneSymbol : notDoneYetSymbol), name,
                localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
