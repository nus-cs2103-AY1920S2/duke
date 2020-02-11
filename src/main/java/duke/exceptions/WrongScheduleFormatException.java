package duke.exceptions;

public class WrongScheduleFormatException extends DukeException {
    @Override
    public String toString() {
        return "Please enter your desired schedule date in the following format.\n"
                + "YYYY-MM-DD";
    }
}
