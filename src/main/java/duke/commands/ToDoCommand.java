package duke.commands;

import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Encapsulates a "todo" command from the user.
 */
public class ToDoCommand implements Command {
    String description;
    
    /**
     * Constructs a new ToDoCommand instance.
     * @param description Task description
     */
    public ToDoCommand(String description) {
        this.description = description;
    }
    
    /**
     * Adds a new ToDo task specified by this ToDoCommand into the task list.
     * @param tasks TaskList object to store tasks
     * @param ui UI object for interfacing with the user
     * @param storage Storage object to read and write TaskList state from files
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null && ui != null && storage != null; //Precondition: non-null arguments
        
        Task newTask = new ToDo(description);
        tasks.addTask(newTask);
        
        ui.showAddTaskMessage(newTask, tasks.size());
    }
    
    /**
     * Returns false, since "todo" is not an exit command.
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
