package duke.exception;

public class DukeNumberFormatException extends DukeException {
    @Override
    public String getMessage() {
        return "OOPS!!! Please give me the task number in Integer.";
    }
}
