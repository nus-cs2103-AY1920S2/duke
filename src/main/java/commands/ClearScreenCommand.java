package commands;

import ui.TextUi;

public class ClearScreenCommand extends Command {
    public static final String COMMAND_WORD = "clr";
    public static final String MESSAGE_USAGE = "Clear the screen.\n"
            + "    Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        TextUi.clearScreen();
        return null;
    }

    @Override
    public CommandResult executeForGUI() {
        TextUi.clearScreen();
        return null;
    }
}
