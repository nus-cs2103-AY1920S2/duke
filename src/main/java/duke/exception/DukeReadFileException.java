package duke.exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class DukeReadFileException extends DukeException {
    public DukeReadFileException(IOException e) {
        super(e.getMessage());
    }
}