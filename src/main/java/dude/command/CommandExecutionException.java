package dude.command;

/**
 * An exception thrown when a command is syntactically correct but is invalid.
 * Eg. done given to a task that is already done.
 * Eg. deleting a index which does not exist.
 */
public class CommandExecutionException extends Exception {

    /**
     * Initializes a CommandExecutionException with the given error message which can be accessed via getMessage().
     *
     * @param message Error message describing how the command given was invalid.
     */
    public CommandExecutionException(String message) {
        super(message);
    }
}
