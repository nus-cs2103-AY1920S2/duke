package duke.command;

import duke.main.UiHandler;
import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * List command implementing command interface.
 */
public class ListCommand implements Command {

    /**
     * Sets ui response to list of task to be displayed.
     * @param task task for this execution
     * @param uiHandler ui handler to capture response
     * @param storage storage to be updated
     * @param taskList list of tasks
     */
    @Override
    public void execute(String task, UiHandler uiHandler, Storage storage, TaskList taskList) {
        uiHandler.setResponse(taskList.toString());
    }

}
