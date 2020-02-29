package duketasks;

/**
 * Represents to-do object that the user has to complete. No deadline
 */

public class Todo extends Task {
    private static final String TODOTASKCODE = "T";

    public Todo(String taskName) {
        super(taskName, TODOTASKCODE);
    }

    /**
     * Constructs a new To-do object based on the task name and whether it's done.
     *
     * @param taskName String of task description
     * @param isDone boolean of whether the task is done or not
     */
    public Todo(String taskName, String isDone) {
        this(taskName);
        if (isDone.equals("O")) {
            this.done();
        }
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", this.taskCode, super.toString());
    }
}
