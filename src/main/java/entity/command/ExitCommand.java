package entity.command;

import entity.TaskList;
import handler.Storage;
import handler.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.sayBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
