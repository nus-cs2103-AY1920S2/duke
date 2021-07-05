package commands;

import ui.TextUi;

/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */

public class IncorrectCommand extends Command {

    public final String feedbackToUser;

    public IncorrectCommand(String commandWord) {
        this.feedbackToUser = commandWord;
    }

    @Override
    public CommandResult execute() {
        TextUi.printAlert();
        return new CommandResult(feedbackToUser);
    }

    @Override
    public CommandResult executeForGUI() {
        TextUi.printAlert();
        return new CommandResult(feedbackToUser);
    }
}
