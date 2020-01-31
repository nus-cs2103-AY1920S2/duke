package duke;

public class DukeException extends Exception {
    public DukeException(String s) {
        // Call constructor of parent Exception
        super(s);
    }

    @Override
    public String toString() {
        return "OOPS!!! " + getMessage();
    }
}
