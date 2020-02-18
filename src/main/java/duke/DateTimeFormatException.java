package duke;

public class DateTimeFormatException extends DukeException {
    @Override
    public String getErrorMessage() {
        return "Please input due date in YYYY-MM-DD format.";
    }
}
