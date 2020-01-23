package e0148811.duke;

public class DukeException extends Exception {
    public DukeException(String message) {
        super("ERROR. Please refer to the following guide and try again:\n" + message);
    }
}
