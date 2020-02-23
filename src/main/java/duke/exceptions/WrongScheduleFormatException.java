package duke.exceptions;

public class WrongScheduleFormatException extends DukeException {
    @Override
    public String toString() {
        return "Patrick, your schedule date needs to be in this format:\n"
                + "YYYY-MM-DD";
    }
}
