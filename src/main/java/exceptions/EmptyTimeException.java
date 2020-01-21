package exceptions;

/**
 * EmptyTimeException is thrown when user does
 * not specify a time/date after a task action.
 *
 * @author ChesterSim
 */
public class EmptyTimeException extends Exception {
    private String taskType;
    private String[] fields;

    public EmptyTimeException(String taskType, String[] fields) {
        super();
        this.taskType = taskType;
        this.fields = fields;
    }

    @Override
    public String toString() {
        String taskType;

        return ":( OOPS!!! The time/date of a " + this.taskType + " cannot be empty.";
    }

    public String[] getFields() {
        return this.fields;
    }

    public String stringifyFields() {
        return this.taskType + " " + this.fields[0] + " /at ";
    }
}
