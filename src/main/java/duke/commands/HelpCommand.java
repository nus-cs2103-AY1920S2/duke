package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Encapsulates a "help" command from the user.
 */
public class HelpCommand implements Command {
    String commandName;
    
    /**
     * Constructs a new `HelpCommand` instance.
     * @param commandName Queried command name
     */
    public HelpCommand(String commandName) {
        this.commandName = commandName;
    }
    
    /**
     * Displays a help entry for the command specified by this `HelpCommand`.
     * @param tasks TaskList object to store tasks
     * @param ui UI object for interfacing with the user
     * @param storage Storage object to read and write TaskList state from files
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null && ui != null && storage != null; //Precondition: non-null arguments
        
        ui.showHelpMessage(commandName);
    }
    
    /**
     * Returns false, since "help" is not an exit command.
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}

