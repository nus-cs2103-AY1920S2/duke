public class DukeInvalidDateFormatException extends DukeException {
    public DukeInvalidDateFormatException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
