package duke.exception;

public class DukeStorageFileException extends DukeException {
    /**
     * Returns an DukeException related to Storage file errors.
     */
    public DukeStorageFileException() {
        super("Unable to retrieve file...");
    }
}
