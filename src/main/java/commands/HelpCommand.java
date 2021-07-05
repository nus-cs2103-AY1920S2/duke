package commands;

import static ui.TextUi.printHelpMessage;

/**
 * Shows help instructions. (after user input is wrong)
 */
public class HelpCommand  extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "    Example: " + COMMAND_WORD;

    public HelpCommand() {
    }

    @Override
    public CommandResult execute() {
        printHelpMessage();
        return new CommandResult("");
    }

    @Override
    public CommandResult executeForGUI() {
        return new CommandResult(
                commands.add.AddTodoCommand.MESSAGE_USAGE_1
                        + "\n" + "  "+ commands.add.AddDeadlineCommand.MESSAGE_USAGE_1
                        + "\n" + "  "+ commands.add.AddEventCommand.MESSAGE_USAGE_1
                        //+ "\n" + "  "+DeleteCommand.MESSAGE_USAGE
                        + "\n" + "  "+ClearCommand.MESSAGE_USAGE_1
                        //+ "\n" + "  "+FindCommand.MESSAGE_USAGE
                        + "\n" + "  "+ ListCommand.MESSAGE_USAGE
                        //+ "\n" + "  "+ ViewCommand.MESSAGE_USAGE
                        //+ "\n" + ViewAllCommand.MESSAGE_USAGE
                        + "\n" + "  "+ HelpCommand.MESSAGE_USAGE
                        + "\n" + "  "+ ExitCommand.MESSAGE_USAGE_1
        );
    }
}
