package duke.task;

public class ToDo extends Task {
    private final String bulletin = "[T]";

    private TaskType type;

    /**
     * Constructor with a name
     * @param name
     */
    public ToDo(String name) {
        super(name);
        type = TaskType.toDo;
    }

    /**
     * Constructor with a name and a boolean
     * @param name
     * @param isDone
     */
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Puts important details into a String that is suitable for storing in files
     * @return a String in the save-file format
     */
    public String encoder() {
        return String.format("T:%s:%d\n", name, (isDone ? 1 : 0));
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
        return String.format("[%s] %s",
                (super.isDone ? doneSymbol : notDoneYetSymbol), name);
    }
}
