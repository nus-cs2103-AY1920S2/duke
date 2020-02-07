package duke.exception;

public class DukeException extends Exception {
    // to avoid warning because of inheriting from a Serializable class
    private static final long serialVersionUID = 1000000;
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
