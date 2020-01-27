package duke.exception;

@SuppressWarnings("serial")
public class DukeReadFileException extends DukeException {
    public DukeReadFileException(Exception e) {
        super(e.getMessage());
    }
}