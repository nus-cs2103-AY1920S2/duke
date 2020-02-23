package duke.exceptions;

public class MissingKeywordException extends DukeException {
    @Override
    public String toString() {
        return "Patrick, nothing in our list contains that word :(\n";
    }
}
