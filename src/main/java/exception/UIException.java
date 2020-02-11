package exception;

public class UIException extends DukeException {
    public UIException(String issue) {
        super(String.format("Dear user, %s", issue));
    }
}
