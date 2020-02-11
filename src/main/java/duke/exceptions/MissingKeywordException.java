package duke.exceptions;

public class MissingKeywordException extends DukeException {
    @Override
    public String toString() {
        return "No such keyword exists.";
    }
}
