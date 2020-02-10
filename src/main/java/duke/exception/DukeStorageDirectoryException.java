package duke.exception;

public class DukeStorageDirectoryException extends DukeException {
    /**
     * Returns an DukeException related to Storage directory errors.
     */
    public DukeStorageDirectoryException() {
        super("Unable to retrieve directory...");
    }
}
