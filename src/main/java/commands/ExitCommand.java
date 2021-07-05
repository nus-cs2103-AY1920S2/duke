package commands;


import common.Messages;

/**
 * Terminates the program.
 */

public class ExitCommand extends Command {

    public ExitCommand() {

    }
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE_1 = COMMAND_WORD + ": Exit the program.";
    public static final String MESSAGE_USAGE_2 = "    Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(Messages.MESSAGE_FAREWELL));
    }

    @Override
    public CommandResult executeForGUI() {
        return new CommandResult(String.format(Messages.MESSAGE_FAREWELL));
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
