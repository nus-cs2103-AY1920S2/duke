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
     * @return String list of tasks
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) { // print out the task list
        return ui.listMsg(taskList.getTaskList());
    }
}
