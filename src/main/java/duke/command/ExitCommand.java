package duke.command;

import duke.main.UiHandler;
import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * Exit command implementing command interface.
 */
public class ExitCommand implements Command {

    /**
     * Exits duke and set appropriate ui response.
     * @param task task for this execution
     * @param uiHandler ui handler to capture response
     * @param storage storage to be updated
     * @param taskList list of tasks
     */
    @Override
    public void execute(String task, UiHandler uiHandler, Storage storage, TaskList taskList) {
        uiHandler.setResponse("bye ciao adios");
        uiHandler.exitDuke();
    }

}
