package duke.tasks;

public class ToDo extends Task {

    /**
     * Constructor for this particular type of Task
     *
     * @param command full raw text entered to create this particular Task
     */
    public ToDo(String command) {
        super(command);
    }

    /**
     * Prints a message indicating that the Task has been added
     */
    @Override
    public void taskAddedMessage() {
        printLine();
        print("==> Added unique ToDo task: " + this);
        printLine();
    }

    /**
     * Provides a String representation of the Task object with tag [T]
     *
     * @return String representation of the Task
     */
    @Override
    public String toString() {
        return "[T] " + this.command;
    }

}
