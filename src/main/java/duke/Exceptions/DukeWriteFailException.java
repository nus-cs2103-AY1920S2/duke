package duke.Exceptions;

public class DukeWriteFailException extends DukeException {
    public DukeWriteFailException() {
        super.message = "For some reason, I was unable to write the changes back to the data file!! :O";
    }
}
