package seedu.duke.exception;

public class InvalidTaskInputException extends DukeException {
    @Override
    public String toString() {
        return "Invalid task input format!\n"
                + "Format:\n"
                + "deadline <activity> /by <date>\n"
                + "or event <activity> /at <date>\n"
                + "or todo <activity>\n"
                + "or done <index>";
    }
}
