package duke;

public class DukeException extends Exception {
    public DukeException(String error) {
        super("D: OOPS!!!" + error);
    }
}
