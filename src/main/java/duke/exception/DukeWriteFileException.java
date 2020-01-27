package duke.exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class DukeWriteFileException extends DukeException {
    public DukeWriteFileException(IOException e) {
        super(e.getMessage());
    }
}