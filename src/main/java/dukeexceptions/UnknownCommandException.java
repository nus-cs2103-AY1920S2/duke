package dukeexceptions;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String exceptionStr) {
        super(exceptionStr);
    }
}
