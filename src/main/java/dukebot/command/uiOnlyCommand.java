package dukebot.command;

import dukebot.LineName;
import dukebot.Storage;
import dukebot.Ui;
import dukebot.tasklist.TaskList;

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
