package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class FindCommand implements Command {
    String searchString;
    
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }
    
    /**
     * Finds and displays tasks whose description matches the query string.
     * @param tasks TaskList object to store tasks
     * @param ui UI object for interfacing with the user
     * @param storage Storage object to read and write TaskList state from files
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null && ui != null && storage != null; //Precondition: non-null arguments
        
        ui.showMatchingTasksMessage();
        
        int c = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.description().indexOf(searchString) != -1) {
                c++;
                ui.showNumberedEntry(c, task);
            }
        }
    }
    
    public boolean isExit() {
        return false;
    }
}

