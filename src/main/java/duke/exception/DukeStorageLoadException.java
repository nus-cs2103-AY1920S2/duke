package duke.exception;

public class DukeStorageLoadException extends DukeException {
    /**
     * Returns an DukeException related to Storage loading errors.
     */
    public DukeStorageLoadException() {
        super("Unable to load from given file...");
    }
}
