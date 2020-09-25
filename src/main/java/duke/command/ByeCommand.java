package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.TasksList;

/**
 * handles the bye command
 */
public class ByeCommand extends Command{
    @Override

    /**
     * mark and object of ByeCommand as an exit command, the main loop checks thi
     * field to see if the Duke needs to close.
     *
     * @param tasksList not used
     * @param ui not used
     * @param storage not used
     * */
    public void execute(TasksList tasks, Ui ui, Storage storage) {
        isExit = true;
    }
}
