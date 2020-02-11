package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Encapsulates a "bye" command from the user.
 * The "bye" command takes in no arguments, and any provided arguments will be ignored.
 */
public class ByeCommand implements Command {
    /**
     * Prints the goodbye message in the UI.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null && ui != null && storage != null; //Precondition: non-null arguments
        
        ui.showBye();
    }
    
    /**
     * Returns true, since "bye" is an exit command.
     * @return true
     */
    public boolean isExit() {
        return true;
    }
}
