public class EmptyFieldException extends DukeException {
    public EmptyFieldException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
