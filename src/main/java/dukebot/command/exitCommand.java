package dukebot.command;

import dukebot.LineName;
import dukebot.Storage;
import dukebot.Ui;
import dukebot.tasklist.TaskList;

public class exitCommand extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.sayLine(LineName.EXIT);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
