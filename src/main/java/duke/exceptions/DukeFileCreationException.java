package duke.exceptions;

public class DukeFileCreationException extends DukeException {
    public DukeFileCreationException() {
        super.message = "Data file could not be created!!";
    }
}
