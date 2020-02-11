package exception;

public class DukeException extends Exception {
    public DukeException(String issue) {
        super(String.format("Oppsie doodle: %s", issue));
    }
}
