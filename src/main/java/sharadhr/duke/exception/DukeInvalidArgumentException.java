package sharadhr.duke.exception;

import sharadhr.duke.command.Command.CommandName;

/**
 * Thrown when arguments to a command are invalid, and not as specified in the docs
 */
public class DukeInvalidArgumentException extends DukeException {
    private static final long serialVersionUID = 5221851007504557801L;

    private CommandName cmdName;
    private String argumentString;

    public DukeInvalidArgumentException(String message, String[] arguments, CommandName command,
                                        String thrownBy) {
        super(message, thrownBy);
        this.argumentString = String.join(" ", arguments);
        this.cmdName = command;
    }

    @Override public String toString() {
        return String.format("%s%nCommand: %s%nArguments: %s%nThrown by: %s", this.getMessage(),
                             this.cmdName, this.argumentString, this.thrownBy);
    }
}