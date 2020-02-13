package duke;

/**
 * Represents a Command entered by the user.
 */
public class Command {
    private CommandType commandType;
    private String[] params;

    /**
     * Constructs a Command object.
     *
     * @param commandType A specific command type entered by the user.
     * @param params The arguments related to the command.
     */
    public Command(CommandType commandType, String[] params) {
        this.commandType = commandType;
        this.params = params;
    }

    /**
     * Returns the specific command type for the command entered by the user.
     *
     * @return Command type.
     */
    public CommandType getCommandType() {
        return commandType;
    }

    /**
     * Returns the arguments related to the command entered by the user.
     *
     * @return Command arguments.
     */
    public String[] getParams() {
        return this.params;
    }
}