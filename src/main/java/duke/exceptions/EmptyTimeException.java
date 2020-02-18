package duke.exceptions;

/**
 * Throws EmptyTimeException when user does
 * not specify a time/date after a task action.
 *
 * @author ChesterSim
 */
public class EmptyTimeException extends Exception {
    private String taskType;

    /**
     * Returns a partially completed output for greater user experience.
     *
     * @param taskType Type of the task.
     */
    public EmptyTimeException(String taskType) {
        super();
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return "The time/date of the " + this.taskType + " cannot be empty.";
    }
}
