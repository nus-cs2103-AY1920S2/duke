package data.exceptions;

public class TaskNotFoundException extends NullPointerException {

    public TaskNotFoundException() {
    }

    public TaskNotFoundException(String message) {
        super(message);
    }
}
