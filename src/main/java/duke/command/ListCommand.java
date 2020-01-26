package duke.command;

import duke.task.TaskList;
import duke.interaction.Ui;
import duke.util.Storage;

public class ListCommand extends Command {

    public void execute(TaskList taskList, Storage storage) {
        Ui.ShowAllTasks(taskList);
    }

    public boolean isExit() {
        return false;
    }
}
