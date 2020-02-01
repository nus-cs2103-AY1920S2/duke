package duke.entity.command;

import duke.entity.TaskList;
import duke.handler.Storage;
import duke.handler.Ui;

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
