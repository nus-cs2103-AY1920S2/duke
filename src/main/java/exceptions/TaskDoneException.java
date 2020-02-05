package exceptions;

public class TaskDoneException extends Exception {
    public TaskDoneException() {};

    public String toString() {
        return "☹ OOPS!!! Task is already completed!";
    }
}
