package duke.command;

import duke.main.Ui;
import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * Exit command implementing command interface
 */
public class ExitCommand implements Command {

    /**
     * Exit duke and set appropriate ui response
     * @param task
     * @param ui
     * @param storage
     * @param taskList
     */
    @Override
    public void execute(String task, Ui ui, Storage storage, TaskList taskList) {
        ui.setResponse("bye ciao adios");
        ui.exitDuke();
    }

}
