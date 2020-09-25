package duke.command;

import duke.io.Storage;
import duke.io.Ui;
import duke.task.TasksList;


/**
 * The handler for the list command
 */
public class ListCommand extends Command{

    /**
     * Lists all the tasks in a numbered order in the tasksList
     *
     * @param tasksList the tasksList to print from
     * @param ui not used
     * @param storage not used
     */
    @Override
    public void execute(TasksList tasksList, Ui ui, Storage storage) {
        ui.printList(tasksList);
    }
}
