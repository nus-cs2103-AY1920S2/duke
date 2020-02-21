package duke.task;

public class Task {
    public enum TaskType {
        TODO, DEADLINE, EVENT, UNKNOWN;
    }

    public static final String doneSymbol = "\u2714";
    public static final String notDoneYetSymbol = "\u2718";
    public static final String toDoCommand = "todo";
    public static final String deadlineCommand = "deadline";
    public static final String eventCommand = "event";

    protected String name;
    protected boolean isDone;

    /**
     * Constructor with a name.
     * @param name
     */
    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    /**
     * Constructor with a name and a boolean.
     * @param name
     * @param isDone
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Place-holder encoder method.
     * @return an empty String.
     */
    public String encoder() {
        return "";
    }

    /**
     * Gets the name of the Task.
     * @return String name.
     */
    public String getName() {
        return name;
    }

    /**
     * Mark the task as Done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Mark the task as Not Done.
     */
    public void markNotDone() {
        isDone = false;
    }

    /**
     * Checks if the task is done.
     * @return True if the Task is done, False otherwise.
     */
    public boolean isDoneTask() {
        return this.isDone;
    }

    /**
     * Get the Task's type.
     * @return UNKNOWN type for a generic Task.
     */
    public TaskType getTaskType() {
        return TaskType.UNKNOWN;
    }

    /**
     * Stringify the object.
     * @return a String representing the Object.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",
                (isDone ? doneSymbol : notDoneYetSymbol), name);
    }
}
