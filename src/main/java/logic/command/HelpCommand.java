package logic.command;

import commons.Duke;
import commons.Messages;

import static java.util.Objects.requireNonNull;

//to fix
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(Duke duke) {
        requireNonNull(duke);
        String help = Messages.HELP;
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
