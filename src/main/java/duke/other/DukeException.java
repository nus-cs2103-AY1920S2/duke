package duke.other;

public class DukeException extends ArrayIndexOutOfBoundsException {
    private String message;
    public DukeException(String message) {
        super(message);
        this.message = message;
    }


    @Override
    public String toString() {
        return this.message;
    }
}
