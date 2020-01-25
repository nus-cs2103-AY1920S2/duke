package dukebot.command;

import dukebot.storage.Storage;
import dukebot.tasklist.TaskList;
import dukebot.ui.LineName;
import dukebot.ui.Ui;

public class uiOnlyCommand extends Command{
    private LineName toSay;

    public uiOnlyCommand(LineName toSay) {
        this.toSay = toSay;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.sayLine(toSay);
    }
}
