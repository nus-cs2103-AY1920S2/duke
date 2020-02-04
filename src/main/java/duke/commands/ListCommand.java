package duke.commands;

import duke.utilities.*;
import duke.Ui;

public class ListCommand extends Command {

    public ListCommand() {

    }

    /**
     * Gets the ArrayList representation of the tasks from TaskList object.
     * Prints the tasks using Ui.
     *
     * @param storage
     * @param taskList
     * @param ui
     * @return true to indicate that this is not an ExitCommand
     */
    @Override
    public boolean execute(Storage storage, TaskList taskList, Ui ui) { // print out the task list
        ui.listMsg(taskList.getTaskList());
        return true;
    }
}
