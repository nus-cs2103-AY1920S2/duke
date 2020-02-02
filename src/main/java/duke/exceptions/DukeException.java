package duke.exceptions;

public class DukeException extends Exception {
    public DukeError error;

    public DukeException(DukeError error) {
        this.error = error;
    }
}

