package dukebot.command;

import dukebot.storage.Storage;
import dukebot.tasklist.TaskList;
import dukebot.ui.LineName;
import dukebot.ui.Ui;

public class UiOnlyCommand extends Command {
    private LineName toSay;

    /**
     * Generates the command.
     *
     * @param toSay  The LineName to say.
     */
    public UiOnlyCommand(LineName toSay) {
        this.toSay = toSay;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.sayLine(toSay);
    }
}
