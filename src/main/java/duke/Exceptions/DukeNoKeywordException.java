package duke.Exceptions;

public class DukeNoKeywordException extends DukeException {
    public DukeNoKeywordException() {
        super.message = "You forgot to tell me what to look for!";
    }
}
