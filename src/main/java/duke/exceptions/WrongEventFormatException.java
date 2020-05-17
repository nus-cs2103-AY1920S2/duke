package duke.exceptions;

public class WrongEventFormatException extends DukeException {
    @Override
    public String toString() {
        return "Patrick, your event needs to be in this format:\n"
                + "Description /at YYYY-MM-DD HHMM\n";
    }
}
