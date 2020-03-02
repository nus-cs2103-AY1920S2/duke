package commons.exceptions;

public class DukeException extends IllegalArgumentException {
    public DukeException(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
