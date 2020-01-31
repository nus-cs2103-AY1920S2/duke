package exceptions;

/**
 * Throws EmptyTimeException when user does
 * not specify a time/date after a task action.
 *
 * @author ChesterSim
 */
public class EmptyTimeException extends Exception {
    private String taskType;
    private String[] fields;

    /**
     * Returns a partially completed output for greater user experience.
     *
     * @param taskType Type of the task.
     * @param fields User input fields.
     */
    public EmptyTimeException(String taskType, String[] fields) {
        super();
        this.taskType = taskType;
        this.fields = fields;
    }

    @Override
    public String toString() {
        String taskType;

        return ":( OOPS!!! The time/date of the " + this.taskType + " cannot be empty.";
    }

    public String[] getFields() {
        return this.fields;
    }

    public String stringifyFields() {
        return this.taskType + " " + this.fields[0] + " /at ";
    }
}
