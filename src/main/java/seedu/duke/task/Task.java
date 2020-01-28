package seedu.duke.task;

public abstract class Task {

    public static final String SEPERATOR = " | ";
    public static final String TRUE_SYMBOL = "O";
    protected static final String FALSE_SYMBOL = "X";

    protected String taskDescription;
    protected boolean isDone;

    Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String toStringForSaving();

    @Override
    public String toString() {
        return String.format("[%s] %s", (isDone ? TRUE_SYMBOL : FALSE_SYMBOL), taskDescription);
    }
}