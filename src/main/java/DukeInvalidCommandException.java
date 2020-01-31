public class DukeInvalidCommandException extends DukeException {

    public DukeInvalidCommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }

}
