package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Exits chat bot and end programme.
 */
public class ExitCommand extends Command {

    /**
     * Execute the ExitCommand. Causes chat bot to terminate.
     *
     * @param tasks Unused.
     * @param ui This is to interact with the user interface, printing Exit message.
     * @param storage Unused.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = "Bye see you again（ｉДｉ）";
        ui.printMsg(msg);
        return msg;
    }

    /**
     * Cause programme to terminate and exit.
     *
     * @return boolean true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
