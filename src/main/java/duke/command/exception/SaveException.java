package duke.command.exception;

/**
 * Represents an error encountered when a command cannot save changes to the save file.
 */
public class SaveException extends CommandException {
    /**
     * Constructs a new {@code SaveException} when a command cannot save changes to the save file.
     */
    public SaveException(String filePath) {
        super("I can't save to this file!: " + filePath);
    }
}
