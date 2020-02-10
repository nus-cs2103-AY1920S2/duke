package duke.command;

import duke.main.UiHandler;
import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * Unknown command implementing command interface.
 */
public class UnknownCommand implements Command {

    /**
     * Sets ui response to inform user that command is not recognized.
     * @param task task for this execution
     * @param uiHandler ui handler to capture response
     * @param storage storage to be updated
     * @param taskList list of tasks
     */
    @Override
    public void execute(String task, UiHandler uiHandler, Storage storage, TaskList taskList) {
        uiHandler.setResponse("I don't understand what you want");
    }

}
