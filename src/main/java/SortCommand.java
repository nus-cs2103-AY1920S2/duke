/**
 * Encapsulates a "sort" command from the user.
 * The "sort" command takes in no arguments, and any provided arguments will be ignored.
 */
public class SortCommand implements Command {
    /**
     * Constructs a new SortCommand instance.
     */
    public SortCommand() {
    }
    
    /**
     * Sorts all tasks in chronological order, then lists the tasks.
     * Tasks without a date (e.g. `Todo`) will be placed after all tasks with a date.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.sort();
        
        ui.showSortMessage();
        for (int i = 0; i < tasks.size(); i++) {
            ui.showNumberedEntry(i + 1, tasks.getTask(i));
        }
    }
    
    /**
     * Returns false, since "sort" is not an exit command.
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}

