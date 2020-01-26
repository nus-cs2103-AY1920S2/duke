package duke;

@SuppressWarnings("serial")
public abstract class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}