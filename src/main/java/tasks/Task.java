package tasks;

public abstract class Task {

    /**
     * Indicates whether or not user has completed the task
     */
    protected boolean isDone;

    /**
     * Stores the main task that user wants to log in raw String
     */
    protected String command;

    /**
     * Utilised in printing
     */
    protected final String horizontalLine = "____________________________________________________________";

    /**
     * Constructor for Task Class
     *
     * @param command full raw text entered as a command
     * @return Task created
     */
    public Task(String command) {
        this.command = command;
        this.isDone = false; // all tasks are created incomplete
    }

    /**
     * Provides String representation of the respective Task
     *
     * @return String representation of Task
     */
    public abstract String toString();

    /**
     * Indicates that the Task's command has been added
     */
    public abstract void taskAddedMessage();

    /**
     * Marks the Task as completed
     */
    public final void doTask() {
        this.isDone = true;
    }

    /**
     * Obtains tick or cross status icon dependent on completion
     *
     * @return a String of a tick(completed) or a cross(incomplete)
     */
    public final String obtainStatusIcon() {
        return (this.isDone) ? "\u2713" : "\u2718";
    }

    /**
     * Prints a horizontal formatting line
     */
    protected final void printLine() {
        print(horizontalLine);
    }

    /**
     * Prints a given String
     *
     * @param s String to be printed
     */
    protected final void print(String s) {
        System.out.println(s);
    }

    /**
     * Finds out if a Task is done or not
     *
     * @return whether a task has been done
     */
    public final boolean getIsDone() {
        return this.isDone;
    }

}
