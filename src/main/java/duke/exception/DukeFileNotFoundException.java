package duke.exception;

import java.io.FileNotFoundException;

@SuppressWarnings("serial")
public class DukeFileNotFoundException extends DukeException {
    public DukeFileNotFoundException(FileNotFoundException e) {
        super(e.getMessage());
    }
}