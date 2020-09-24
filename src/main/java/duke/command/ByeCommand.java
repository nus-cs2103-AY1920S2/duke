package duke.command;

import duke.io.Storage;
import duke.io.Ui;
import duke.task.TasksList;

public class ByeCommand extends Command{
    @Override
    public void execute(TasksList tasks, Ui ui, Storage storage) {
        isExit = true;
    }
}
