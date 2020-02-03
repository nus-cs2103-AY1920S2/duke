package duke.command;

import duke.main.Ui;
import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * List command implementing command interface
 */
public class ListCommand implements Command {

    /**
     * set ui response to list of task to be displayed
     * @param task
     * @param ui
     * @param storage
     * @param taskList
     */
    @Override
    public void execute(String task, Ui ui, Storage storage, TaskList taskList) {
        ui.setResponse(taskList.toString());
    }

}
