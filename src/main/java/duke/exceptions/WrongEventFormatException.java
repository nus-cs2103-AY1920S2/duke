package duke.exceptions;

public class WrongEventFormatException extends DukeException {
    @Override
    public String toString() {
        return "Please enter your event in the following format:\n"
                + "Description /at YYYY-MM-DD HHMM";
    }
}
