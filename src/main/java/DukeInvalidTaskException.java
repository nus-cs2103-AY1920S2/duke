public class DukeInvalidTaskException extends DukeException {

    public DukeInvalidTaskException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }

}
