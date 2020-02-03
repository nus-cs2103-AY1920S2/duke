package duke.command;

import duke.main.Ui;
import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * Unknown command implementing command interface
 */
public class UnknownCommand implements Command {

    /**
     * set ui response to inform user that command is not recognized
     * @param task
     * @param ui
     * @param storage
     * @param taskList
     */
    @Override
    public void execute(String task, Ui ui, Storage storage, TaskList taskList) {
        ui.setResponse("I don't understand what you want");
    }

}
