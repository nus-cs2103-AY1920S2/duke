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
