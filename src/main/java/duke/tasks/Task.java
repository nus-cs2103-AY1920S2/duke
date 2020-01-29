package duke.tasks;

/**
 * Abstract task class for which includes the most common features among To-Do, Deadline, Event.
 */
public abstract class Task {

    /**
     * Indicates whether or not user has completed the task.
     */
    protected boolean isDone;

    /**
     * Stores the main task that user wants to log in raw String.
     */
    protected String command;

    /**
     * Constructor for Task Class.
     *
     * @param command full raw text entered as a command.
     */
    public Task(String command) {
        this.command = command;
        this.isDone = false; // all tasks are created incomplete
    }

    /**
     * Provides String representation of the respective Task.
     *
     * @return String representation of Task.
     */
    public abstract String toString();

    /**
     * Indicates that the Task's command has been added.
     */
    public abstract void taskAddedMessage();

    /**
     * Marks the Task as completed.
     */
    public final void doTask() {
        this.isDone = true;
    }

    /**
     * Obtains tick or cross status icon dependent on completion.
     *
     * @return a String of a tick(completed) or a cross(incomplete).
     */
    public final String obtainStatusIcon() {
        return (this.isDone) ? "V" : "X";
    }

    /**
     * Prints a horizontal formatting line.
     */
    protected final void printLine() {
        print(TasksConstant.FORMAT_LINE);
    }

    /**
     * Prints a given String.
     *
     * @param s String to be printed.
     */
    protected final void print(String s) {
        System.out.println(s);
    }

    /**
     * Finds out if a Task is done or not.
     *
     * @return whether a task has been done.
     */
    public final boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Obtains the Task's (irrelevant of subtype) actual command/action to complete.
     *
     * @return The Task-of-interest's command/action.
     */
    public final String getCommand() {
        return this.command;
    }

}
