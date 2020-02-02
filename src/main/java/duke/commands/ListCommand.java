package duke.commands;
import duke.utilities.*;
import duke.Ui;

public class ListCommand extends Command {

    public ListCommand() {

    }

    @Override
    public boolean execute (Storage storage, TaskList taskList, Ui ui) { // print out the task list
        ui.listMsg(taskList.getTaskList());
        return true;
    }
}
