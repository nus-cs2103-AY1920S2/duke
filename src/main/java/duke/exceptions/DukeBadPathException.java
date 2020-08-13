package duke.exceptions;

import java.nio.file.Path;

/**
 * Handles event where path to data file does not exist.
 */
public class DukeBadPathException extends DukeException {
    public DukeBadPathException(Path path) {
        super.message = "The path " + path + " does not exist!";
    }
}
