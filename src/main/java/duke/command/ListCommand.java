package duke.command;

import duke.io.Storage;
import duke.io.Ui;
import duke.task.TasksList;

public class ListCommand extends Command{
    @Override
    public void execute(TasksList tasksList, Ui ui, Storage storage) {
        ui.printTasksList(tasksList);
    }
}
