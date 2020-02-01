package duke.entity.command;

import duke.entity.TaskList;
import duke.handler.Storage;
import duke.handler.Ui;

public class ListCommand extends Command {

    public ListCommand() {

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.listAllTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
