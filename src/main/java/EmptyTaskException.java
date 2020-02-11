/**
 * This class is the exception in the case where there is no task description.
 */
public class EmptyTaskException extends Exception {

    String taskType;

    public EmptyTaskException(String task) {
        super("Oops!");
        taskType = task;
    }

    @Override
    public String toString() {
        return "Oops! The description of a task cannot be empty";
    }
}
